package com.example.cs246team22.printingapp;

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

}
