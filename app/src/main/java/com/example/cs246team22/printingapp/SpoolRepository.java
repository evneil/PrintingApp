package com.example.cs246team22.printingapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SpoolRepository {

    private SpoolDao mSpoolDao;
    private LiveData<List<Spool>> mAllSpools;


    SpoolRepository(Application application) {
        SpoolRoomDatabase db = SpoolRoomDatabase.getDatabase(application);
        mSpoolDao = db.spoolDao();
        mAllSpools = mSpoolDao.getAllSpools();

    }

    LiveData<List<Spool>> getAllSpools() {
        return mAllSpools;
    }

    //Must Be run in background
    public void insert (Spool spool) {

        new insertAsyncTask(mSpoolDao).execute(spool);
    }

    private static class insertAsyncTask extends AsyncTask<Spool, Void, Void> {

        private SpoolDao mAsyncTaskDao;

        insertAsyncTask(SpoolDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Spool... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}