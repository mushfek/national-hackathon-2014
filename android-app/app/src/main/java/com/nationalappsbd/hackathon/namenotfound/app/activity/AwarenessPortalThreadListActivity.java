package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
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
@ContentView(R.layout.awareness_thread_list)
public class AwarenessPortalThreadListActivity extends RoboListActivity {

    private static final Logger log = Logger.getLogger(AwarenessPortalThreadListActivity.class);

    private static final String EXTRA_AWARENESS_CATEGORY_ID = "awareness_category_id";
    private static final String EXTRA_AWARENESS_PORTAL_THREAD_ID = "awareness_portal_thread_id";

    private int categoryId;

    @InjectView(R.id.new_thread)
    private AddFloatingActionButton addNewFloatingButton;

    @Inject
    private CommonService commonService;

    @Inject
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryId = getIntent().getExtras().getInt(EXTRA_AWARENESS_CATEGORY_ID);

        //TODO: call service to fetch open threads associated to the corresponding category

        setListAdapter(new AwarenessPortalThreadListAdapter(commonService.getThreadList()));

        addNewFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AwarenessPortalThreadListActivity.this, AwarenessPortalNewThreadActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, AwarenessPortalConversationActivity.class);
        intent.putExtra(EXTRA_AWARENESS_PORTAL_THREAD_ID, ((OpenThread) getListAdapter().getItem(position)).getId());

        startActivity(intent);
    }

    private class AwarenessPortalThreadListAdapter extends ArrayAdapter<OpenThread> {

        public AwarenessPortalThreadListAdapter(List<OpenThread> openThreadList) {
            super(AwarenessPortalThreadListActivity.this, 0, openThreadList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.awareness_thread_list_item, parent, false);
            }

            TextView threadTitleView = ViewHolder.get(convertView, R.id.thread_title);
            TextView partialPostView = ViewHolder.get(convertView, R.id.partial_post);
            TextView userAliasView = ViewHolder.get(convertView, R.id.user_alias);
            TextView postTimeView = ViewHolder.get(convertView, R.id.post_time);

            OpenThread openThread = getItem(position);

            threadTitleView.setText(openThread.getTitle());
            partialPostView.setText(getPartial(openThread.getThreadBody()) + "...");
            userAliasView.setText(openThread.getPostedBy());
            postTimeView.setText(openThread.getPostDate());

            return convertView;
        }
    }

    // NOTE: I know the most "khaat" method but play along cuz "kisu korar nai" :|
    private String getPartial(String originalText) {
        return originalText.substring(0, 10);
    }
}
