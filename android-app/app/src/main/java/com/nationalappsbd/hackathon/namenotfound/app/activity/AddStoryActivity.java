package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Story;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_story_add)
public class AddStoryActivity extends RoboActivity {
    private Logger log = Logger.getLogger(AddStoryActivity.class);

    private static final int MAX_COUNT = 5000;
    public static final String STORY_KEY = "_story_key_";

    @InjectView(R.id.category)
    private Spinner category;

    @InjectView(R.id.iExperienced)
    private RadioButton iExperienced;

    @InjectView(R.id.iSaw)
    private RadioButton iSaw;

    @InjectView(R.id.pledge)
    private RadioGroup pledge;

    @InjectView(R.id.story)
    private EditText story;

    @InjectView(R.id.counter)
    private TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        story.addTextChangedListener(textWatcher);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.category));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

    private boolean isValid() {
        boolean isValid = true;
        if (category.getSelectedItemId() <= 0) {
            isValid = false;
            ((TextView) category.getChildAt(0)).setError("This field is required");
        } else {
            ((TextView) category.getChildAt(0)).setError(null);
        }

        if (story.getText().length() == 0) {
            isValid = false;
            story.setError("This field is required");
        } else {
            story.setError(null);
        }

        if (pledge.getCheckedRadioButtonId() == -1) {
            isValid = false;
            Toast.makeText(this, "Please select whether you experienced this or saw this", Toast.LENGTH_LONG).show();
        }

        return isValid;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_next) {
            if (isValid()) {
                moveToNextActivity();
            }
        }
    }

    private void moveToNextActivity() {
        Story storyDomain = new Story();
        storyDomain.setCategory(category.getSelectedItem().toString());
        int checkedRadioButtonId = pledge.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = (RadioButton) findViewById(checkedRadioButtonId);
            storyDomain.setPledge(radioButton.getText().toString());
        }

        storyDomain.setStory(story.getText().toString());

        Intent intent = new Intent(this, AddStoryLocationActivity.class);
        intent.putExtra(STORY_KEY, storyDomain);
        startActivity(intent);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            int value = s.length();
            setMessageCounter(MAX_COUNT + "/" + value);
        }

        public void afterTextChanged(Editable s) {
        }
    };

    private void setMessageCounter(String s) {
        counter.setText(s);
    }
}
