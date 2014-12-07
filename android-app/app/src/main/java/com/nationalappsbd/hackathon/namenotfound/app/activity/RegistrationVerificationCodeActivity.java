package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_registration_verification_code)
public class RegistrationVerificationCodeActivity extends RoboActivity {
    public static String KEY_MOBILE_NUMBER = "key_mobile_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onClickVerify(View view) {
        Intent intent = new Intent(RegistrationVerificationCodeActivity.this, RegistrationInformation.class);
        startActivity(intent);
    }
}
