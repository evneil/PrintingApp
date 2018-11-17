package com.example.cs246team22.printingapp;

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



        if (requestCode == NEW_SPOOL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Spool spool = new Spool(tName, tBrand, tColor, tWeight, tMaterial);
            mSpoolViewModel.insert(spool);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "One or more fields empty, not saved",
                    Toast.LENGTH_LONG).show();
        }

    }

}
