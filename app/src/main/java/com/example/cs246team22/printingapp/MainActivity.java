package com.example.cs246team22.printingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final String SID_KEY = "sid";
    private SpoolViewModel mSpoolViewModel;

    public static final int NEW_SPOOL_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpoolViewModel = ViewModelProviders.of(this).get(SpoolViewModel.class);



    }



    public void StartAddSpool(View view){

        Intent AddSpoolActivityIntent = new Intent(this, AddSpoolActivity.class);
        startActivity(AddSpoolActivityIntent);
    }



}
