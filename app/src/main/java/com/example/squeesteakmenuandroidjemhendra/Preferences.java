package com.example.squeesteakmenuandroidjemhendra;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_MEJA ="meja";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setMeja(Context context, String nomorMeja){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_MEJA, nomorMeja);
        editor.apply();
    }

    public static String getMeja(Context context){
        return getSharedPreference(context).getString(KEY_MEJA,"");
    }

    public static void clear (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_MEJA);
        editor.apply();
    }
}
