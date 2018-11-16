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
    private String brandName;
    @ColumnInfo(name = "length")
    private int spoolLength;
    @ColumnInfo(name = "type")
    private String spoolType;
    @ColumnInfo(name = "weight")
    private String spoolWeight;
    @ColumnInfo(name = "color")
    private String spoolColor;

    // constructors

    // properties
    public void setSpoolID(int id) {
        this.spoolID = spoolID;
    }
    public void setBrandName(String BrandName) {
        this.brandName = brandName;
    }
    public void setSpoolLength(int SpoolLength) { this.spoolLength = spoolLength;}
    public void setSpoolType(String spoolType) { this.spoolType = spoolType;}
    public void setSpoolWeight(String spoolWeight) { this.spoolWeight = spoolWeight;}
    public void setSpoolColor( String spoolColor) {this.spoolColor = spoolColor;}


    public int getSpoolID() {

        return spoolID;
    }

    public String getBrandName() {

        return brandName;
    }

    public int getSpoolLength() {
        return spoolLength;
    }

    public String getSpoolType() {
        return spoolType;
    }

    public String getSpoolWeight() {
        return spoolWeight;
    }

    public String getSpoolColor() {
        return spoolColor;
    }
}

