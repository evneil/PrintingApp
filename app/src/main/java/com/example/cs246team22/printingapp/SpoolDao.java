package com.example.cs246team22.printingapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SpoolDao {

    //Insert a Spool
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Spool spool);

    //Insert All Spools
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Spool> spool);

    //Update Spool
    @Update
    void updateSpools(Spool... spool);

    //Get all spools from table // Can set ORDER BY *value*
    @Query("SELECT * from spool_table ORDER BY name")
    LiveData<List<Spool>> getAllSpools();

    //Delete All Spool Data
    @Query("DELETE FROM spool_table")
    void deleteAll();

    //Delete a spool
    @Delete
    void deleteSpool(Spool spool);




}

