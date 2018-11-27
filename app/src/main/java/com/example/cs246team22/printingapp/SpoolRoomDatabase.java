package com.example.cs246team22.printingapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Spool.class}, version = 1, exportSchema = false)
public abstract class SpoolRoomDatabase extends RoomDatabase {

    public abstract SpoolDao spoolDao();
    private static volatile SpoolRoomDatabase INSTANCE;

    static SpoolRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SpoolRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SpoolRoomDatabase.class, "spool_database")

                            //.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * This starts the database with default values by deleting the database and the
     *  repopulating.
     *  THIS MUST BE CHANGED IN FINAL OBVIOUSLY
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SpoolDao mDao;

        PopulateDbAsync(SpoolRoomDatabase db) {
            mDao = db.spoolDao();
        }



            @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Spool spool = new Spool(0,"Roger" , "Amazon", "Orange", 300, "PLA");
            mDao.insert(spool);
            spool = new Spool(0,"Try" , "Wal-mart", "Blue", 500, "Nylon");
            mDao.insert(spool);


                return null;
        }

        public static void destroyInstance() {
            INSTANCE = null;
        }

    }
}