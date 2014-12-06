package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.OpenThread;
import com.nationalappsbd.hackathon.namenotfound.app.util.misc.AppUtils;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mushfekur Rahman
 */
@ContentView(R.layout.awareness_thread_add_new)
public class AwarenessPortalNewThreadActivity extends RoboActivity {

    private static final Logger log = Logger.getLogger(AwarenessPortalNewThreadActivity.class);

    @InjectView(R.id.thread_title)
    private EditText threadTitleTextView;

    @InjectView(R.id.thread_content)
    private EditText threadContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onNewThreadPostClick(View view) {
        //TODO: gotta do some validation crap

        String threadTitle = threadTitleTextView.getText().toString().trim();
        String threadContent = threadContentTextView.getText().toString().trim();

        OpenThread newThread = new OpenThread(AppUtils.randInt(), threadTitle, threadContent, "Jane Doe", getCurrentDate());

        //TODO: write a crappy awareness service to save the newly created thread

        Intent intent = new Intent(this, AwarenessPortalThreadAddSuccessActivity.class);
        startActivity(intent);
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        return dateFormat.format(new Date());
    }
}
