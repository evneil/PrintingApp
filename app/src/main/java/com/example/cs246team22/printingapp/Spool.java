package com.example.cs246team22.printingapp;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "spool_table")
public class Spool {
    // fields
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int spoolID;

    @ColumnInfo(name = "manufacturer")
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
    public void setManufacturerName(String manufacturer) {
        this.manufacturerName = manufacturer;
    }
    public String getManufacturerName() {
        return this.manufacturerName;
    }
}

