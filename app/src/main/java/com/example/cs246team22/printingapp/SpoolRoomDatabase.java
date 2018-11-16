package com.example.cs246team22.printingapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Spool.class}, version = 1)
public abstract class SpoolRoomDatabase extends RoomDatabase {

    public abstract SpoolDao spoolDao();

    private static volatile SpoolRoomDatabase INSTANCE;

    //on open this will populate the database
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    //Here we choose how the database is populated.
    //Maybe in the future it will be if onlineDatabase sync and populate else sync from memory
    //For now it deletes everything MUST CHANGE THIS LATER
    //Then adds a few values THIS MUST ALSO CHANGE we want to only populate with user data
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SpoolDao mDao;

        PopulateDbAsync(SpoolRoomDatabase db) {
            mDao = db.spoolDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Spool spool = new Spool(1,"John" , "Amazon", "Orange", 500 , "PLA");
            mDao.insert(spool);
            spool = new Spool(1,"Edward" , "Walmart", "Red", 300 , "PLA");
            mDao.insert(spool);
            return null;
        }
    }

    static SpoolRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (SpoolRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SpoolRoomDatabase.class, "spool_database")
                            .addCallback(sRoomDatabaseCallback)
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
