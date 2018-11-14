package com.example.cs246team22.printingapp;

public class Spool {
    // fields
    private int spoolID;
    private String manufacturerName;
    // constructors
    public Spool() {}
    public Spool(int id, String manufacturer) {
        this.spoolID = id;
        this.manufacturerName = manufacturer;
    }
    // properties
    public void setID(int id) {
        this.spoolID = id;
    }
    public int getID() {
        return this.spoolID;
    }
    public void setmanufacturerName(String manufacturer) {
        this.manufacturerName = manufacturer;
    }
    public String getmanufacturerName() {
        return this.manufacturerName;
    }
}

