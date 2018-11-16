package com.example.cs246team22.printingapp;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class SpoolDBAsyc {

    private static final String TAG = SpoolDBAsyc.class.getName();
    public static void populateAsync(@NonNull final SpoolRoomDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    public static void populateSync(@NonNull final SpoolRoomDatabase db) {
        populateWithTestData(db);
    }
    private static Spool addSpool(final SpoolRoomDatabase db, Spool spool) {
        db.spoolDao().insertAll(spool);
        return spool;
    }
    private static void populateWithTestData(SpoolRoomDatabase db) {
        Spool spool = new Spool();
        spool.setSpoolName("Maverik");
        spool.setSpoolBrand("Amazon");
        spool.setSpoolColor("Orange");
        spool.setSpoolWeight(500);
        spool.setSpoolMaterial("PLA");
        addSpool(db, spool);
        List<Spool> spoolList = db.spoolDao().getAllSpools();
        Log.d(SpoolDBAsyc.TAG, "Rows Count Name: " + db.spoolDao().findByName("Jekson").getSpoolName());
        for (int i = 0; i < spoolList.size(); i++) {
            Log.d(SpoolDBAsyc.TAG, "Rows Name: " + spoolList.get(i).getSpoolName());
        }
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final SpoolRoomDatabase mDb;
        PopulateDbAsync(SpoolRoomDatabase db) {
            mDb = db;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            populateWithTestData(mDb);
            return null;
        }
    }
}