package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Complaint;
import com.nationalappsbd.hackathon.namenotfound.app.service.ComplaintFactory;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_complaint)
public class ComplaintActivity extends RoboActivity {

    private static final int MAX_COUNT = 5000;

    private static final int REQ_SELECT_PHOTO = 111;
    private static final int REQ_SELECT_VIDEO = 222;
    private static final int REQ_SELECT_AUDIO = 333;

    private static final int DOC_TYPE_IMAGE = 1;
    private static final int DOC_TYPE_VIDEO = 2;
    private static final int DOC_TYPE_AUDIO = 3;

    private static final String PHONE_NUMBER = "0167100000";

    @InjectView(R.id.iExperienced)
    private RadioButton iExperienced;
    @InjectView(R.id.iSaw)
    private RadioButton iSaw;
    @InjectView(R.id.pledge)
    private RadioGroup pledge;
    @InjectView(R.id.title)
    private EditText title;
    @InjectView(R.id.story)
    private EditText story;
    @InjectView(R.id.counter)
    private TextView counter;
    @InjectView(R.id.contact_number)
    private EditText contactNumber;
    @InjectView(R.id.user_name)
    private EditText username;
    @InjectView(R.id.relaion_ship)
    private EditText relaionship;
    @InjectView(R.id.btn_next)
    private Button btnnext;
    @InjectView(R.id.otherContactNumber)
    private LinearLayout otherContactNumber;

    @InjectView(R.id.attachmentLayout)
    private LinearLayout attachmentLayout;
    @InjectView(R.id.attachmentLink)
    private TextView attachmentLink;

    @InjectView(R.id.cross)
    private ImageView cross;

    private String attachmentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        story.addTextChangedListener(textCountWatcher);
        contactNumber.setText(PHONE_NUMBER);
        contactNumber.addTextChangedListener(contactNumberWatcher);
    }

    private boolean isValid() {
        boolean isValid = true;

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

        if (contactNumber.getText().length() == 0) {
            contactNumber.setError("This field is required");
            isValid = false;
        }

        if (!contactNumber.getText().toString().trim().equals(PHONE_NUMBER)) {
            if (username.getText().length() == 0) {
                username.setError("This field is required");
                isValid = false;
            } else {
                username.setError(null);
            }

            if (relaionship.getText().length() == 0) {
                relaionship.setError("This field is required");
                isValid = false;
            } else {
                relaionship.setError(null);
            }
        }

        return isValid;
    }

    public void uploadImageOptionClicked(View view) {

        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {   //KITKAT
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }

        startActivityForResult(intent, REQ_SELECT_PHOTO);
    }

    public void uploadVideoOptionClicked(View view) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {   //KITKAT
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        }

        startActivityForResult(intent, REQ_SELECT_VIDEO);
    }

    public void uploadAudioOptionClicked(View view) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {   //KITKAT
            intent.setType("audio/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        }

        startActivityForResult(intent, REQ_SELECT_AUDIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            attachmentLayout.setVisibility(View.VISIBLE);
            switch (requestCode) {
                case REQ_SELECT_AUDIO:
                    attachmentPath = getPath(data.getData(), DOC_TYPE_AUDIO);
                    setAttachmentLink(attachmentPath);
                    break;
                case REQ_SELECT_PHOTO:
                    attachmentPath = getPath(data.getData(), DOC_TYPE_IMAGE);
                    setAttachmentLink(attachmentPath);
                    break;
                case REQ_SELECT_VIDEO:
                    attachmentPath = getPath(data.getData(), DOC_TYPE_VIDEO);
                    setAttachmentLink(attachmentPath);
                    break;
            }
        }
    }

    private void setAttachmentLink(String link) {
        attachmentLink.setText("<a href='" + link + "'> Attachment<a>");
    }

    private String getPath(Uri uri, int documentType) {
        if (uri.getLastPathSegment().split(":").length > 1) {
            return getPathForNewStyleUri(uri, documentType);
        } else {
            return getPathForOldStyleUri(uri, documentType);
        }
    }

    /*
     * http://stackoverflow.com/questions/20260416/retrieve-absolute-path-when-select-image-from-gallery-kitkat-android#answer-20343022
     */
    private String getPathForNewStyleUri(Uri uri, int documentType) {
        String dataColumn;
        String idColumn;
        Uri internalContentUri;
        Uri externalContentUri;

        switch (documentType) {
            case DOC_TYPE_IMAGE:
                dataColumn = MediaStore.Images.Media.DATA;
                idColumn = MediaStore.Images.Media._ID;
                internalContentUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
                externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                break;

            case DOC_TYPE_VIDEO:
                dataColumn = MediaStore.Video.Media.DATA;
                idColumn = MediaStore.Video.Media._ID;
                internalContentUri = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
                externalContentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                break;

            case DOC_TYPE_AUDIO:
                dataColumn = MediaStore.Audio.Media.DATA;
                idColumn = MediaStore.Audio.Media._ID;
                internalContentUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
                externalContentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                break;
            default:
                return null;
        }

        Uri storageUri = !Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
                ? internalContentUri : externalContentUri;
        String id = uri.getLastPathSegment().split(":")[1];
        Cursor cursor = managedQuery(storageUri, new String[]{dataColumn}, idColumn + "=" + id, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(dataColumn));
        }

        return null;
    }

    /**
     * http://stackoverflow.com/questions/2169649/get-pick-an-image-from-androids-built-in-gallery-app-programmatically#answer-2636538
     */
    private String getPathForOldStyleUri(Uri uri, int documentType) {
        String dataColumn;

        switch (documentType) {
            case DOC_TYPE_IMAGE:
                dataColumn = MediaStore.Images.Media.DATA;
                break;

            case DOC_TYPE_VIDEO:
                dataColumn = MediaStore.Video.Media.DATA;
                break;

            case DOC_TYPE_AUDIO:
                dataColumn = MediaStore.Audio.Media.DATA;
                break;

            default:
                return null;
        }

        // Try to retrieve the file from the media store first. This will only work for files selected from gallery.
        Cursor cursor = managedQuery(uri, new String[]{dataColumn}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndexOrThrow(dataColumn));
        }

        // this is our fallback here
        return uri.getPath();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_complaint, menu);
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

    private final TextWatcher textCountWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            int value = s.length();
            setMessageCounter(MAX_COUNT + "/" + value);
        }

        public void afterTextChanged(Editable s) {
        }
    };

    private final TextWatcher contactNumberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String mobileNumber = charSequence.toString().trim();
            if (!mobileNumber.equals(PHONE_NUMBER)) {
                enableOtherContactNumber(true);
            } else {
                enableOtherContactNumber(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void enableOtherContactNumber(boolean enable) {
        if (enable) {
            otherContactNumber.setVisibility(View.VISIBLE);
        } else {
            otherContactNumber.setVisibility(View.GONE);
        }
    }

    private void setMessageCounter(String s) {
        counter.setText(s);
    }

    public void onClick(View view) {
        if (isValid()) {
            Complaint complaint = new Complaint();

            int checkedRadioButtonId = pledge.getCheckedRadioButtonId();
            if (checkedRadioButtonId != -1) {
                RadioButton radioButton = (RadioButton) findViewById(checkedRadioButtonId);
                complaint.setPledge(radioButton.getText().toString());
            }
            complaint.setTitle(title.getText().toString());
            complaint.setStory(story.getText().toString());
            complaint.setAttachmentPath(attachmentPath);
            complaint.setMobile(contactNumber.getText().toString());
            complaint.setUserName(username.getText().toString());
            complaint.setRelationShip(relaionship.getText().toString());

            ComplaintFactory.getInstance().addComplaint(complaint);

            moveToNextActivity();
        }
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(this, ComplaintListActivity.class);
        startActivity(intent);
    }

    public void onCrossButton(View view) {
        attachmentLink.setText("");
        attachmentLayout.setVisibility(View.GONE);
        attachmentPath = null;
    }
}
