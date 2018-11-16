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

    @ColumnInfo(name = "name")
    private String spoolName;

    @ColumnInfo(name = "brand")
    private String spoolBrand;

    @ColumnInfo(name = "color")
    private String spoolColor;

    @ColumnInfo(name = "weight")
    private int spoolWeight;

    @ColumnInfo(name = "material")
    private String spoolMaterial;

/*
    public Spool( int spoolID, String spoolName, String spoolBrand, String spoolColor, int spoolWeight, String spoolMaterial) {
        this.spoolID = spoolID;
        this.spoolName = spoolName;
        this.spoolColor = spoolColor;
        this.spoolWeight = spoolWeight;
        this.spoolMaterial = spoolMaterial;
    }
*/

    // properties
    public void setSpoolID(int spoolID) { this.spoolID = spoolID;}
    public void setSpoolName(String spoolName) { this.spoolName = spoolName;}
    public void setSpoolBrand(String spoolBrand) { this.spoolBrand = spoolBrand;}
    public void setSpoolColor( String spoolColor) {this.spoolColor = spoolColor;}
    public void setSpoolWeight(int spoolWeight) { this.spoolWeight = spoolWeight;}
    public void setSpoolMaterial(String spoolMaterial) { this.spoolMaterial = spoolMaterial;}



    public int getSpoolID() { return spoolID;}

    public String getSpoolName() { return spoolName;}

    public String getSpoolBrand() { return spoolBrand;}

    public String getSpoolColor() {
        return spoolColor;
    }

    public int getSpoolWeight() { return spoolWeight;}

    public String getSpoolMaterial() { return spoolMaterial;}




}

