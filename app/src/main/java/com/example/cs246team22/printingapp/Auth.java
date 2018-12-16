package com.example.cs246team22.printingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class Auth {

    private UserController controller;

    public Auth(Context context) {
        this.controller = new UserController(context);
    }

    public static String hash(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(pass.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            String hash = sb.toString();
            return hash;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void makeAccount(String email, String name, String pass) {

        Log.d("test", "account process started");

        String pHash = hash(pass);

        List<String> names = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        List<User> users = controller.getUsers();

        Log.d("test", "" + users.isEmpty());

        Log.d("test", "lists generated");

        if (!users.isEmpty()) {
            for (User user : users) {
                Log.d("test", "user found, or not?");
                names.add(user.getName());
                emails.add(user.getEmail());
            }
        }

        Log.d("test", "lists populated");

        if (!emails.isEmpty() && !names.isEmpty()) {
            if (!emails.contains(email) && !names.contains(name)) {
                Log.d("test", "this might be the problem");
                //User account = getAccount(email, pass);
                controller.addUser(new User(email, name, pHash));
            }
        }

        Log.d("test", "working good");
    }

    public User getAccount(String identification, String pass) {
        String pHash = hash(pass);
        if (checkHash(identification, pHash)) {
            User user = controller.getUser(identification);
            return user;
        }
        return null;
    }

    private boolean checkHash(String identification, String pHash) {

        boolean idIsEmail = false; if (identification.contains("@")) { idIsEmail = true; }

        List<User> users = controller.getUsers();
        for (User user : users) {
            if (idIsEmail) {
                if (user.getEmail().equals(identification) && user.getHash().equals(pHash)) {
                    return true;
                }
            }
            else {
                if (user.getName().equals(identification) && user.getHash().equals(pHash)) {
                    return true;
                }
            }
        }
        return false;
    }


}
