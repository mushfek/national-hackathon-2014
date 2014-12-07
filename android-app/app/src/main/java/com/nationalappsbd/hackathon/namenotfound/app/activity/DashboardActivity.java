package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.util.view.adapter.ViewHolder;
import com.nationalappsbd.hackathon.namenotfound.app.util.view.widget.DialogBuilder;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class DashboardActivity extends RoboActivity {

    @Inject
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mainLabel.setText("Hello From Team: Name Not Found \n(Seriously guys??!! We can't find a name yet?)");
    }

    public void onCounselingSelection(View view) {
        DialogBuilder.buildCounsellingAgreementDialog(this, getString(R.string.agreement_message),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent counsellingMessagingActivityIntent = new Intent(DashboardActivity.this, CounselingActivity.class);
                        counsellingMessagingActivityIntent.putExtra(CounselingActivity.EXTRA_USER_ID, 1); //TODO: set actual userId here
                        startActivity(counsellingMessagingActivityIntent);
                    }
                }).show();
    }

    public void onHeatMapSelection(View view) {
        Intent heatmapIntent = new Intent(this, HeatmapActivity.class);

        startActivity(heatmapIntent);
    }

    public void onAwarenessPortalSelection(View view) {
        Intent awarenessIntent = new Intent(this, AwarenessPortalCategoryListActivity.class);

        startActivity(awarenessIntent);
    }

    public void onFileComplaintSelection(View view) {
        Intent intent = new Intent(this, ComplaintActivity.class);

        startActivity(intent);
    }

    public void onEmergencyHelpSelection(View view) {
        showDropDown();
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

    public void showDropDown() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.phone_number);
        builderSingle.setTitle("Emergency Help");

        List<EmergencyCall> emergencyCalls = new ArrayList<EmergencyCall>();

        EmergencyCall emergencyCall = new EmergencyCall();
        emergencyCall.id = R.drawable.track;
        emergencyCall.text = "Track Me";
        emergencyCalls.add(emergencyCall);

        emergencyCall = new EmergencyCall();
        emergencyCall.id = R.drawable.polish_station;
        emergencyCall.text = "Call National Help Line 10921";
        emergencyCalls.add(emergencyCall);

        emergencyCall = new EmergencyCall();
        emergencyCall.id = R.drawable.polish_station;
        emergencyCall.text = "Call Polish Station";
        emergencyCalls.add(emergencyCall);

        emergencyCall = new EmergencyCall();
        emergencyCall.id = R.drawable.phone_number;
        emergencyCall.text = "Call Personal Contact 1";
        emergencyCalls.add(emergencyCall);

        emergencyCall = new EmergencyCall();
        emergencyCall.id = R.drawable.phone_number;
        emergencyCall.text = "Call Personal Contact 2";
        emergencyCalls.add(emergencyCall);

        EmergencyCallAdapter emergencyCallAdapter = new EmergencyCallAdapter(emergencyCalls);

        builderSingle.setAdapter(emergencyCallAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String strName = emergencyCallAdapter.getItem(which);
//                        AlertDialog.Builder builderInner = new AlertDialog.Builder(DashboardActivity.this);
//                        builderInner.setMessage(strName);
//                        builderInner.setTitle("Your Selected Item is");
//                        builderInner.setPositiveButton("Ok",
//                                new DialogInterface.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(
//                                            DialogInterface dialog,
//                                            int which) {
//                                        dialog.dismiss();
//                                    }
//                                });
//                        builderInner.show();
                    }
                });
        builderSingle.show();
    }

    class EmergencyCallAdapter extends ArrayAdapter<EmergencyCall> {

        public EmergencyCallAdapter(List<EmergencyCall> callList) {
            super(DashboardActivity.this, 0, callList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.emergency_help_list_view_single_item, parent, false);
            }

            EmergencyCall item = getItem(position);
            ImageView imageView = ViewHolder.get(convertView, R.id.icon);
            imageView.setImageResource(item.id);
            TextView itemName = ViewHolder.get(convertView, R.id.item_name);

            itemName.setText(item.text);

            return convertView;
        }
    }

    class EmergencyCall {
        public String text;
        public int id;
    }

}
