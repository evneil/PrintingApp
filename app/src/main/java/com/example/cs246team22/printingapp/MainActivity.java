package com.example.cs246team22.printingapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SpoolViewModel mSpoolViewModel;

    public static final int NEW_SPOOL_ACTIVITY_REQUEST_CODE = 1;
    public static final int PRINT_JOB_ACTIVITY_REQUEST_CODE = 2;
    public static final int WEIGHT_ACTIVITY_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.spoolList);
        final SpoolListAdapter adapter = new SpoolListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get current ViewModel
        mSpoolViewModel = ViewModelProviders.of(this).get(SpoolViewModel.class);


        //observer for live data, needed to recycler view
        mSpoolViewModel.getAllSpools().observe(this, new Observer<List<Spool>>() {
            @Override
            public void onChanged(@Nullable final List<Spool> spools) {
                // Update the cached copy of the words in the adapter.
                adapter.setSpools(spools);
            }
        });


        Log.d("test","app created");

    }

    public void printJob(View view) {
        Intent intent = new Intent(this, PrintJobActivity.class);
        startActivityForResult(intent, PRINT_JOB_ACTIVITY_REQUEST_CODE );
        Log.d("test","print clicked");
    }

    public void onWeight(View view) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivityForResult(intent, WEIGHT_ACTIVITY_REQUEST_CODE );
        Log.d("test","weight clicked");
    }


    public void StartAddSpool(View view){

        Intent AddSpoolActivityIntent = new Intent(this, AddSpoolActivity.class);
        startActivityForResult(AddSpoolActivityIntent, NEW_SPOOL_ACTIVITY_REQUEST_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String tName = data.getStringExtra(AddSpoolActivity.REPLY_NAME);
        String tBrand =data.getStringExtra(AddSpoolActivity.REPLY_BRAND);
        String tColor =data.getStringExtra(AddSpoolActivity.REPLY_COLOR);
        int    tWeight = data.getIntExtra(AddSpoolActivity.REPLY_WEIGHT, 0);
        String tMaterial =data.getStringExtra(AddSpoolActivity.REPLY_MATERIAL);

        int numID = data.getIntExtra(WeightActivity.SPOOL_ID, 0);
        int numWeight = data.getIntExtra(WeightActivity.NEW_WEIGHT, 0);
        int numIDp = data.getIntExtra(PrintJobActivity.SPOOL_ID_PRINT, 0);
        int numWeightp = data.getIntExtra(PrintJobActivity.NEW_WEIGHT_PRINT, 0);

        if (requestCode == NEW_SPOOL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Spool spool = new Spool(0,tName, tBrand, tColor, tWeight, tMaterial);
            mSpoolViewModel.insert(spool);

        }
        //there is a lot going on here, the database only has an insert function, I don't know how
        //to write an update function. There's also not a working delete function.
        //when I say not working I mean not implemented fully

        //ok so it turns out that finding the spool and then just changing the weight and then inesrting
        //it is the same as updating it... so it works but idk if this is the way you're supposed
        //to do it
        else if (requestCode == WEIGHT_ACTIVITY_REQUEST_CODE) {
            LiveData<List<Spool>> spoolData = mSpoolViewModel.getAllSpools();
            List<Spool> spools = spoolData.getValue();

            for (Spool s : spools) {
                if (s.getSpoolID() == numID) {
                    Spool tempSpool = s;
                    tempSpool.setSpoolWeight(numWeight);
                    mSpoolViewModel.insert(tempSpool);
                    Log.d("test","spool found and added, old one not deleted cuz idk how to do that");
                }
            }

            Log.d("test","" + spoolData);


        }
        else if (requestCode == PRINT_JOB_ACTIVITY_REQUEST_CODE) {
            LiveData<List<Spool>> spoolData = mSpoolViewModel.getAllSpools();
            List<Spool> spools = spoolData.getValue();

            Log.d("test","idp:" + numIDp);
            Log.d("test","weightp:" + numWeightp);

            for (Spool s : spools) {
                Log.d("test","there are spools and we're lookin for em");
                Log.d("test","s id: " + s.getSpoolID());
                if (s.getSpoolID() == numIDp) {
                    Spool tempSpool = s;
                    tempSpool.setSpoolWeight(s.getSpoolWeight() - numWeightp);
                    mSpoolViewModel.insert(tempSpool);
                    Log.d("test","spool found and weight modified");
                }
            }

            Log.d("test","print job -> " + spoolData);


        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "One or more fields empty, not saved",
                    Toast.LENGTH_LONG).show();
        }

    }

}
