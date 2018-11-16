package com.example.cs246team22.printingapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Spool.class}, version = 1)
public abstract class SpoolRoomDatabase extends RoomDatabase {

    public abstract SpoolDao spoolDao();

    private static volatile SpoolRoomDatabase INSTANCE;

    static SpoolRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (SpoolRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SpoolRoomDatabase.class, "spool_database")

                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
