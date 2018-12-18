package com.example.cs246team22.printingapp;

/**
 * User class, nothing special. Straightforward. For all intents and purposes
 * this is just a data storage class.
 *
 * @author Aiden
 * @version 1.0
 * @since 12/17/18
 */
public class User {

    private String hash;
    private String email;
    private String name;

    public User(String email, String name, String hash) {
        this.email = email;
        this.name = name;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    /**
     * returns a string of all the variables in the user class instance formatted nicely
     *
     * @return
     */
    public String userToString() {
        return "Email: " + this.email + " | Name: " + this.name + " | Hash: " + this.hash;
    }

}