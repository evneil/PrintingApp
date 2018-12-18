package com.example.cs246team22.printingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * Auth is an authentication handler, it has a controller that manages the user. Auth
 * does not manage the user, besides one instance, deleting the user. Auth technically
 * handles all of the functions that come from buttons on the Authentication Activity screen
 *
 * @author Aiden
 * @version 1.5
 * @since 12/17/18
 */
public class Auth {

    private UserController controller;

    public Auth(Context context) {
        this.controller = new UserController(context);
    }

    /**
     * hashes the string with an md5 hash, not the best protection, but seeing as
     * nothing is really needing to be protected this is the alternative to the
     * failed google firebase integration
     *
     * @param pass
     * @return
     */
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

    /**
     * recieves an email, name, and password as entered by the user, it attempts to make
     * an instance of User, however, it will only do so provided that there isn't already
     * an active user saved.
     *
     * @param email
     * @param name
     * @param pass
     */
    public void makeAccount(String email, String name, String pass) {

        Log.d("test", "Making account");

        String pHash = hash(pass);

        User user = controller.getUser();

        if(user == null) {
            controller.setUser(new User(email, name, pHash));
            controller.writeUser();
        }
        else if (!user.getEmail().equals(email) || !user.getName().equals(name)) {
            return;
        }
        else {
            controller.setUser(new User(email, name, pHash));
            controller.writeUser();
        }

        Log.d("test", "User after register: " + controller.getUser().userToString());

    }

    /**
     * identification could be email or name, whichever the user decided to login with.
     * Either way it doesn't matter as long as the password matches with the password saved
     * in conjunction with a matching identification string. Once a mathch is found it will
     * return the instance of User that matches it. In this case it will only every have the
     * potential of being the saved user since there is only one active user at a time.
     * If there does not seem to be a matching pair then it returns null
     *
     * @param identification
     * @param pass
     * @return
     */
    public User getAccount(String identification, String pass) {

        Log.d("test", "Getting account");

        String pHash = hash(pass);

        if (checkHash(identification, pHash))
            return controller.getUser();

        return null;

    }

    /**
     * function to check a matching pair. If the identification matches either the email
     * or the name of the saved user and the password is the same then true is returned.
     * False otherwise
     *
     * @param identification
     * @param pHash
     * @return
     */
    private boolean checkHash(String identification, String pHash) {

        boolean idIsEmail = false; if (identification.contains("@")) { idIsEmail = true; }

        User user = controller.getUser();

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


        return false;
    }

    /**
     * Clears the shared preferences. This would normally not be an added function but
     * this is more of a proof of concept authentication in the first place and it is useful
     * for debugging
     */
    public void deleteUser() {
        Log.d("test", "Attempting to delete user");
        controller.deleteUser();
    }


}