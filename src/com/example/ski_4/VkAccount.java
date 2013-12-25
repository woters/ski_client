package com.example.ski_4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created with IntelliJ IDEA.
 * User: Alyosha
 * Date: 25.11.13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class VkAccount {
    public String access_token;
    public long user_id;

    public void save(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("access_token", access_token);
        editor.putLong("user_id", user_id);
        editor.commit();
    }

    public void restore(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        access_token = prefs.getString("access_token", null);
        user_id = prefs.getLong("user_id", 0);
    }
}
