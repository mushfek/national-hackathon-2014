package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_registration_mobile_number)
public class RegistrationMobileNumberActivity extends RoboActivity {

    @InjectView(R.id.mobile_number)
    private EditText mobileNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(RegistrationMobileNumberActivity.this, RegistrationVerificationCodeActivity.class);
        intent.putExtra(RegistrationVerificationCodeActivity.KEY_MOBILE_NUMBER, mobileNumberEditText.getText().toString());
        startActivity(intent);
    }
}
