package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

/**
 * @author Mushfekur Rahman
 */
@ContentView(R.layout.awareness_thread_add_success)
public class AwarenessPortalThreadAddSuccessActivity extends RoboActivity {

    private static final String EXTRA_AWARENESS_CATEGORY_ID = "awareness_category_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewThreadListClick(View view) {
        Intent intent = new Intent(this, AwarenessPortalThreadListActivity.class);
        intent.putExtra(EXTRA_AWARENESS_CATEGORY_ID, 4343);

        startActivity(intent);
    }

    public void onAddNewThreadClick(View view) {
        Intent intent = new Intent(this, AwarenessPortalNewThreadActivity.class);
        startActivity(intent);
    }
}
