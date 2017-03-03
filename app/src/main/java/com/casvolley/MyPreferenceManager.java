package com.casvolley;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.casvolley.Models.UserModel;


public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "lms";

    // All Shared Preferences Keys
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_TYPE = "user_type";
    private static final String KEY_USER_PHONE = "user_phone";


    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void storeUser(UserModel user) {
        editor.clear();
        editor.commit();
        editor.putString(KEY_USER_ID, user.getUser_id());
        editor.putString(KEY_USER_NAME, user.getUser_name());
        editor.commit();


        Log.e(TAG, "User is stored in shared preferences. " + user.getUser_name() + " ," + user.getUser_id() );
    }

    public UserModel getUser() {
        if (pref.getString(KEY_USER_ID, null) != null) {
            String id, name,type,phone;
            id = pref.getString(KEY_USER_ID, null);
            name = pref.getString(KEY_USER_NAME, null);
            type = pref.getString(KEY_USER_TYPE, null);
            phone = pref.getString(KEY_USER_PHONE, null);

           UserModel userModel = new UserModel();
            userModel.setUser_id(id);
            userModel.setUser_name(name);
            return userModel;
        }
        return null;
    }
    public void clear() {
        editor.clear();
        editor.commit();
    }
}
