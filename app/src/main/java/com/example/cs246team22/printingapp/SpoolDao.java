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


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Spool spool);

    @Insert
    void insertAll(Spool... spool);

    @Update
    void updateSpools(Spool... spool);

    @Query("SELECT * FROM spool_table where name LIKE  :spoolBrand ")
    Spool findByName(String spoolBrand);;

    @Query("DELETE FROM spool_table")
    void deleteAll();

    @Delete
    void delete(Spool spool);

    @Query("SELECT * from spool_table ORDER BY id ASC")
    LiveData<List<Spool>> getAllSpools();

}

