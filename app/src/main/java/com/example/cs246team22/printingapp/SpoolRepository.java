package com.example.cs246team22.printingapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

class SpoolRepository {

    private SpoolDao mSpoolDao;
    private LiveData<List<Spool>> mAllSpools;

    SpoolRepository(Application application) {
        SpoolRoomDatabase db = SpoolRoomDatabase.getDatabase(application);
        mSpoolDao = db.spoolDao();
        mAllSpools = mSpoolDao.getAllSpools();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Spool>> getAllSpools() {
        return mAllSpools;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(Spool spool) {
        new insertAsyncTask(mSpoolDao).execute(spool);
    }
    void update(Spool... spool) { new updateAsyncTask(mSpoolDao).execute(spool);}
    void deleteSpool(Spool spool) { new deleteSpoolAsyncTask(mSpoolDao).execute(spool);}


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

    private static class updateAsyncTask extends AsyncTask<Spool, Void, Void> {

        private SpoolDao mAsyncTaskDao;

        updateAsyncTask(SpoolDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Spool... params) {
            mAsyncTaskDao.updateSpools(params[0]);
            return null;
        }
    }

    private static class deleteSpoolAsyncTask extends AsyncTask<Spool, Void, Void> {
        private SpoolDao mAsyncTaskDao;

        deleteSpoolAsyncTask(SpoolDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Spool... params) {
            mAsyncTaskDao.deleteSpool(params[0]);
            return null;
        }
    }
}

