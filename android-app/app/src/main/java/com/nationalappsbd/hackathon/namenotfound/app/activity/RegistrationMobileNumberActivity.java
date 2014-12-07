package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.util.misc.SharedPreferencesUtils;
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
        if("done".equals(SharedPreferencesUtils.getStringFromPreference(this, "registration"))) {
            Intent intent = new Intent(RegistrationMobileNumberActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(RegistrationMobileNumberActivity.this, RegistrationVerificationCodeActivity.class);
        intent.putExtra(RegistrationVerificationCodeActivity.KEY_MOBILE_NUMBER, mobileNumberEditText.getText().toString());
        startActivity(intent);
    }
}
