package com.example.cs246team22.printingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Manages functions related to the user. When dealing with users you should use this class
 * instead of doing it manually, especially if dealing with sharedpreferences.
 *
 * @author Aiden
 * @version 1.0
 * @since 12/17/18
 */
public class UserController {

    private User user;
    private Context context;
    private SharedPreferences prefs;

    public UserController(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("usercontroller", Context.MODE_PRIVATE);
        this.user = readUser();
    }

    public User getUser() {
        if (user == null)
            return null;
        return user;
    }

    public User getUser(String identification) {

        boolean idIsEmail = false; if (identification.contains("@")) { idIsEmail = true; }

        if (idIsEmail && user.getEmail().equals(identification)) {
            return user;
        }
        else if (!idIsEmail && user.getName().equals(identification)) {
            return user;
        }

        return null;
    }

    public void setUser(User user) {

        this.user = user;

    }

    /**
     * deserializes the user saved in the stored preferences and returns the user
     * found. Make sure to have a null check.
     *
     * @return
     */
    public User readUser() {

        Gson gson = new Gson();
        String json = prefs.getString("User", "");
        if (json == "") {
            return null;
        }
        User user = gson.fromJson(json, User.class);
        Log.d("test", "Read in: " + user.userToString());
        return user;

    }

    /**
     * serializes the user to the stored preferences
     */
    public void writeUser() {

        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.user);
        editor.putString("User", json);
        editor.commit();

    }

    /**
     * clears the shared preference stored data. Do not use this unless you know what that
     * means.
     */
    public void deleteUser() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

}