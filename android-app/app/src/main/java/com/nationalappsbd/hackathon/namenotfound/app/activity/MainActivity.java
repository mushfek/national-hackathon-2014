package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    //  @InjectView(R.id.main_label)
    //  TextView mainLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mainLabel.setText("Hello From Team: Name Not Found \n(Seriously guys??!! We can't find a name yet?)");
    }

    public void onCounsellingSelection(View view) {
        Intent counsellingMessagingActivityIntent = new Intent(this, CounsellingMessagingActivity.class);
        counsellingMessagingActivityIntent.putExtra(CounsellingMessagingActivity.EXTRA_USER_ID, 1); //TODO: set actual userId here

        startActivity(counsellingMessagingActivityIntent);
    }

    public void onHeatMapSelection(View view) {
        Intent heatmapIntent = new Intent(this, HeatmapActivity.class);

        startActivity(heatmapIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
