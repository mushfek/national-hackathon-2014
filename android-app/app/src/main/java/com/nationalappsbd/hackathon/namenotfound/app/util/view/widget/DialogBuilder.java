package com.nationalappsbd.hackathon.namenotfound.app.util.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.nationalappsbd.hackathon.namenotfound.app.R;

/**
 * @author Mushfekur Rahman
 */
public class DialogBuilder {
    public static Dialog buildCounsellingAgreementDialog(Context context, String agreementMessage,
                                                         DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.counselling_agreement)
                .setMessage(agreementMessage)
                .setPositiveButton(R.string.agreed, listener)
                .setNegativeButton(R.string.no, null)
                .create();
    }
}
