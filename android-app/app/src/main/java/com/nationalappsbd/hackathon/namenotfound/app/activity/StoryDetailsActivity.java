package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Story;
import com.nationalappsbd.hackathon.namenotfound.app.service.StoryFactory;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

public class StoryDetailsActivity extends RoboFragmentActivity {

    public static final String STORY_DETAILS_KEY = "_story_details_";

    @InjectView(R.id.counselling_msg_text)
    private TextView counsellingmsgtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conselling_message);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Long storyId = getIntent().getExtras().getLong(STORY_DETAILS_KEY);
        Story story = StoryFactory.getInstance().findById(storyId);

        counsellingmsgtext.setText(story.getStory());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
