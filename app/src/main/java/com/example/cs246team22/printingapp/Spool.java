package com.example.cs246team22.printingapp;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.security.Timestamp;
import java.util.Date;
/** Represents a spool of filament.
 * @author Edward Neil
 * @version 1.0
 * @since 1.0
 */
@Entity(tableName = "spool_table")
public class Spool {

    @PrimaryKey
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

    /** Creates a spool with product information
     * @param spoolID The Spool's ID.
     * @param spoolName The spool's name.
     * @param spoolBrand The spool's brand name.
     * @param spoolColor The spool's color.
     * @param spoolWeight The spool's weight in grams.
     * @param spoolMaterial The spool's material.
     */
    public Spool(int spoolID, String spoolName, String spoolBrand, String spoolColor
            , int spoolWeight, String spoolMaterial) {

        this.spoolID = spoolID;
        this.spoolName = spoolName;
        this.spoolBrand = spoolBrand;
        this.spoolColor = spoolColor;
        this.spoolWeight = spoolWeight;
        this.spoolMaterial = spoolMaterial;
    }

    // properties


    //
    /*
    public void setSpoolName(String spoolName) { this.spoolName = spoolName;}
    public void setSpoolBrand(String spoolBrand) { this.spoolBrand = spoolBrand;}
    public void setSpoolColor( String spoolColor) {this.spoolColor = spoolColor;}
    public void setSpoolWeight(int spoolWeight) { this.spoolWeight = spoolWeight;}
    public void setSpoolMaterial(String spoolMaterial) { this.spoolMaterial = spoolMaterial;}

    */
    /** Gets the spool id.
     * @return An int representing the spool's ID
     */
    public int getSpoolID() { return this.spoolID;}

    /** Gets the spool name.
     * @return A string representing the spool's name
     */
    public String getSpoolName() { return this.spoolName;}

    /** Gets the spool brand
     * @return A string representing the spool's brand
     */
    public String getSpoolBrand() { return this.spoolBrand;}

    /** Gets the spool color.
     * @return A string representing the spool's color
     */
    public String getSpoolColor() {
        return this.spoolColor;
    }

    /** Gets the spool name.
     * @return An int representing the spool's weight in grams
     */
    public int getSpoolWeight() { return this.spoolWeight;}

    /** Gets the spool's material.
     * @return A string representing the spool's material
     */
    public String getSpoolMaterial() { return this.spoolMaterial;}


    /** Sets the spools' ID.
     * @param spoolID The Spool's ID.
     */
    public void setSpoolID(int spoolID) { this.spoolID = spoolID;}

    /** Sets the spool's name
     * @param spoolName The Spool's name.
     */
    public void setSpoolName(String spoolName) {
        this.spoolName = spoolName;
    }

    /** Sets the spool's brand.
     * @param spoolBrand The Spool's brand name.
     */
    public void setSpoolBrand(String spoolBrand) {
        this.spoolBrand = spoolBrand;
    }

    /** Sets the spool's color
     * @param spoolColor The Spool's color.
     */
    public void setSpoolColor(String spoolColor) {
        this.spoolColor = spoolColor;
    }

    /** Sets the spool's weight.
     * @param spoolWeight The Spool's eight in grams.
     */
    public void setSpoolWeight(int spoolWeight) {
        this.spoolWeight = spoolWeight;
    }

    /** Sets the spool's material.
     * @param spoolMaterial The Spool's material.
     */
    public void setSpoolMaterial(String spoolMaterial) {
        this.spoolMaterial = spoolMaterial;
    }


}

