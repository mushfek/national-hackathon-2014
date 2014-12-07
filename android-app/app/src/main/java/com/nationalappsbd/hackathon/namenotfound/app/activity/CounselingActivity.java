package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.service.CommonService;
import com.nationalappsbd.hackathon.namenotfound.app.util.view.adapter.ViewHolder;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Message;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * @author Mushfekur Rahman
 */
@ContentView(R.layout.counselling_message_list)
public class CounselingActivity extends RoboListActivity {

    private static final Logger log = Logger.getLogger(CounselingActivity.class);

    public static final String EXTRA_USER_ID = "user_id";

    private int userId;
    private CounsellingMessageListAdapter messageListAdapter;
    private List<Message> messageList;

    @InjectView(R.id.edit_message)
    private EditText messageEditor;

    @InjectResource(R.string.sender_me)
    private String senderYou;
    @InjectResource(R.string.sender_counselor)
    private String senderCounselor;

    @Inject
    private CommonService commonService;

    @Inject
    protected LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        userId = getIntent().getExtras().getInt(EXTRA_USER_ID);
        log.debug("userId={}", userId);

        // TODO: call necessary service to fetch message list of corresponding user
        messageList = commonService.getMessageList(1);

        // setting view adapter
        messageListAdapter = new CounsellingMessageListAdapter(messageList);
        setListAdapter(messageListAdapter);
        messageListAdapter.notifyDataSetChanged();
        getListView().setSelection(messageList.size()-1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

    public void sendMessage(View view) {
        String newMessage = messageEditor.getText().toString().trim();
        if (newMessage.length() > 0) {
            messageEditor.setText("");

            Message m = new Message(5, -1, newMessage, "06-12-2014", "12:10 AM", false);
            addNewMessage(m);
        }
    }

    private void addNewMessage(Message m) {
        messageList.add(m);
        messageListAdapter.notifyDataSetChanged();

        getListView().setSelection(messageList.size()-1);
    }

    private class CounsellingMessageListAdapter extends ArrayAdapter<Message> {

        public CounsellingMessageListAdapter(List<Message> messageList) {
            super(CounselingActivity.this, 0, messageList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.user_message, parent, false);
            }

            ImageView senderAvatarView = ViewHolder.get(convertView, R.id.sender_avatar);
            TextView senderAliasView = ViewHolder.get(convertView, R.id.sender_alias);
            TextView sentDateView = ViewHolder.get(convertView, R.id.sent_date);
            TextView sendTimeView = ViewHolder.get(convertView, R.id.sent_time);
            TextView messageBodyView = ViewHolder.get(convertView, R.id.counselling_msg_text);

            Message message = getItem(position);

            if (message.isSentByCounselor()) {
                senderAliasView.setText(senderCounselor);
//                messageBodyView.setBackgroundColor(Color.parseColor());
            } else {
                senderAliasView.setText(senderYou);
//                messageBodyView.setBackgroundColor(Color.parseColor());
            }

            sentDateView.setText(message.getSentDate());
            sendTimeView.setText(message.getSentTime());
            messageBodyView.setText(message.getMessageBody());

            return convertView;
        }
    }
}
