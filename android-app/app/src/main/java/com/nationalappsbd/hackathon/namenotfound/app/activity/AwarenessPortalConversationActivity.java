package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.service.CommonService;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

/**
 * @author Mushfekur Rahman
 */
@ContentView(R.layout.awareness_conversation)
public class AwarenessPortalConversationActivity extends RoboActivity {

    @Inject
    private LayoutInflater inflater;

    @Inject
    private CommonService commonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
