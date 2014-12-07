package com.nationalappsbd.hackathon.namenotfound.app.util.misc;

import android.content.Context;

/**
 * @author Abdullah Al Mamun Oronno
 */
public class SharedPreferencesUtils {
    public static final String SHARED_PREFERENCE = "prottoyee_shared_pref";

    public static String getStringFromPreference(Context context, String key) {
        return context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).
                getString(key, null);
    }

    public static void putStringInPreference(Context context, String key, String value) {
        context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .commit();
    }

    public static long getLongFromPreference(Context context, String key) {
        return context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).getLong(key, 0);
    }

    public static void putLongInPreference(Context context, String key, Long value) {
        context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
                .edit()
                .putLong(key, value)
                .commit();
    }
}
