package com.example.cs246team22.printingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class WeightActivity extends AppCompatActivity {

    public static final String SPOOL_ID = "com.example.cs246team22.printingapp.SPOOL_ID";
    public static final String NEW_WEIGHT = "com.example.cs246team22.printingapp.NEW_WEIGHT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Log.d("test","weight created");
    }

    public void onConfirm(View view) {

        Intent reply = new Intent();
        EditText id = (EditText) findViewById(R.id.spoolID);
        EditText newWeight = (EditText) findViewById(R.id.weightCost);
        int numID = Integer.parseInt(id.getText().toString());
        int numWeight = Integer.parseInt(newWeight.getText().toString());

        //add checks to make sure the values are there

        reply.putExtra(SPOOL_ID, numID);
        reply.putExtra(NEW_WEIGHT, numWeight);
        setResult(RESULT_OK, reply);

        Log.d("test","finished collecting weight");

        finish();
    }

}
