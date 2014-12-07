package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Complaint;
import com.nationalappsbd.hackathon.namenotfound.app.service.ComplaintFactory;
import com.nationalappsbd.hackathon.namenotfound.app.util.view.adapter.ViewHolder;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboListActivity;
import roboguice.inject.ContentView;

import java.util.List;

@ContentView(R.layout.activity_complaint_list)
public class ComplaintListActivity extends RoboListActivity {
    private static final Logger log = Logger.getLogger(ComplaintListActivity.class);

    @Inject
    protected LayoutInflater inflater;

    private ComplaintListAdapter complaintListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.debug("onCreate()");

        List<Complaint> complaintList = ComplaintFactory.getInstance().getComplaintList();
        log.debug("Size ={}", complaintList.size());

        complaintListAdapter = new ComplaintListAdapter(complaintList);
        setListAdapter(complaintListAdapter);
        complaintListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_complaint_list, menu);
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

    private class ComplaintListAdapter extends ArrayAdapter<Complaint> {
        List<Complaint> complaints;

        public ComplaintListAdapter(List<Complaint> objects) {
            super(ComplaintListActivity.this, 0, objects);
            complaints = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            log.debug("getView()");

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.complaint_list_single_item, parent, false);
            }

            Complaint complaint = complaints.get(position);

            TextView title = ViewHolder.get(convertView, R.id.complaint_title);
            title.setText(complaint.getTitle());

            TextView story = ViewHolder.get(convertView, R.id.complaint_description);
            story.setText(complaint.getStory());

            return convertView;
        }
    }
}
