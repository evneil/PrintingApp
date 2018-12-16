package com.example.cs246team22.printingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private List<User> users = new ArrayList<>();
    private Context context;
    private SharedPreferences prefs;

    public UserController(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("usercontroller", Context.MODE_PRIVATE);
        readUsers();
        Log.d("test", "well this is working at least");
    }

    public List<User> getUsers() {
        Log.d("test", "stop moving your whole hand to backspace");
        Log.d("test", "" + users.isEmpty());
        Log.d("test", "stop moving your whole hand to backspace");
        Log.d("test", "" + users.size());
        return users;
    }

    public User getUser(String identification) {
        boolean idIsEmail = false; if (identification.contains("@")) { idIsEmail = true; }
        for (User user : users) {
            if (idIsEmail && user.getEmail().equals(identification)) {
                return user;
            }
            else if (!idIsEmail && user.getName().equals(identification)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        writeUsers();
        //if user doesn't exist already
    }

    public void readUsers() {
        Gson gson = new Gson();
        String json = prefs.getString("Users", "");
        this.users = gson.fromJson(json, new TypeToken<List<User>>(){}.getType());
        Log.d("test", "read the things in with the readUsers()");
    }

    public void writeUsers() {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("Users", json);
        editor.commit();
    }

}
