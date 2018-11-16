package com.example.cs246team22.printingapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;


public class SpoolViewModel extends AndroidViewModel {

    private SpoolRepository mRepository;

    private LiveData<List<Spool>> mAllSpools;

    public SpoolViewModel(Application application) {
        super(application);
        mRepository = new SpoolRepository(application);
        mAllSpools = mRepository.getAllSpools();
    }

    LiveData<List<Spool>> getAllSpools() {
        return mAllSpools;
    }

    void insert(Spool spool) {
        mRepository.insert(spool);
    }

}