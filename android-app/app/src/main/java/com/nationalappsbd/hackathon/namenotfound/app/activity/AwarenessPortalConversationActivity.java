package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Message;
import com.nationalappsbd.hackathon.namenotfound.app.domain.OpenThread;
import com.nationalappsbd.hackathon.namenotfound.app.service.CommonService;
import com.nationalappsbd.hackathon.namenotfound.app.util.view.adapter.ViewHolder;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * @author Mushfekur Rahman
 */
@ContentView(R.layout.awareness_conversation)
public class AwarenessPortalConversationActivity extends RoboListActivity {

    private static final Logger log = Logger.getLogger(AwarenessPortalConversationActivity.class);

    private static final String EXTRA_AWARENESS_PORTAL_THREAD_ID = "awareness_portal_thread_id";

    @InjectView(R.id.thread_title)
    private TextView threadTitleView;
    @InjectView(R.id.thread_content)
    private TextView threadContentView;
    @InjectView(R.id.posted_by)
    private TextView postedByView;
    @InjectView(R.id.post_time)
    private TextView postDateView;

    @InjectView(R.id.new_comment)
    private EditText newCommentEditText;

    private int threadId;
    private List<Message> conversations;
    private AwarenessPortalConversationAdapter conversationAdapter;

    @Inject
    private LayoutInflater inflater;

    @Inject
    private CommonService commonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        threadId = getIntent().getExtras().getInt(EXTRA_AWARENESS_PORTAL_THREAD_ID);
        log.debug("Selected threadId={}", threadId);

        //TODO: call service to fetch all conversations related to this thread
        OpenThread openThread = new OpenThread(323, "Thread title",
                "djflsdjf dfjlskdjflskdjf djflsdkjflsdkjfsldkjflsd djfsdlkjflsdkfjldkjfkd",
                "Violet1", "7th Dec, 2014 10:24 AM");

        populateThreadView(openThread);

        //TODO: call service to fetch messages associated to this thread
        conversations = commonService.getMessageList(1);

        conversationAdapter = new AwarenessPortalConversationAdapter(conversations);
        setListAdapter(conversationAdapter);
    }

    public void onPostNewComment(View view) {
        String newMessage = newCommentEditText.getText().toString().trim();
        if (newMessage.length() > 0) {
            newCommentEditText.setText("");

            Message m = new Message(5, -1, newMessage, "06-12-2014", "12:10 AM", false);

            conversations.add(m);
            conversationAdapter.notifyDataSetChanged();

            //TODO: call as service to store these comments
        }
    }

    private void populateThreadView(OpenThread openThread) {
        threadTitleView.setText(openThread.getTitle());
        threadContentView.setText(openThread.getThreadContent());
        postedByView.setText(openThread.getPostedBy());
        postDateView.setText(openThread.getPostDate());
    }

    private class AwarenessPortalConversationAdapter extends ArrayAdapter<Message> {

        public AwarenessPortalConversationAdapter(List<Message> commentList) {
            super(AwarenessPortalConversationActivity.this, 0, commentList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.user_message, parent, false);
            }

            TextView senderAliasView = ViewHolder.get(convertView, R.id.sender_alias);
            TextView sentDateView = ViewHolder.get(convertView, R.id.sent_date);
            TextView sendTimeView = ViewHolder.get(convertView, R.id.sent_time);
            TextView messageBodyView = ViewHolder.get(convertView, R.id.counselling_msg_text);

            Message message = getItem(position);

            senderAliasView.setText("Violet1");
            sentDateView.setText(message.getSentDate());
            sendTimeView.setText(message.getSentTime());
            messageBodyView.setText(message.getMessageBody());

            return convertView;
        }
    }
}
