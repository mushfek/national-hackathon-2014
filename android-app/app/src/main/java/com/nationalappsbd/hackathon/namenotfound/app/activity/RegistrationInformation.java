package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_registration_information)
public class RegistrationInformation extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(RegistrationInformation.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
