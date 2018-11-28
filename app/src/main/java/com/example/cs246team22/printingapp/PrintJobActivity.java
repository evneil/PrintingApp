package com.example.cs246team22.printingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class PrintJobActivity extends AppCompatActivity {

    public static final String SPOOL_ID_PRINT = "com.example.cs246team22.printingapp.SPOOL_ID_PRINT";
    public static final String NEW_WEIGHT_PRINT = "com.example.cs246team22.printingapp.NEW_WEIGHT_PRINT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_job);
        Log.d("test","print created");
    }

    public void onConfirmPrint(View view) {

        Intent reply = new Intent();
        EditText idp = (EditText) findViewById(R.id.spoolIDp);
        EditText newWeightp = (EditText) findViewById(R.id.weightCostp);
        int numIDp = Integer.parseInt(idp.getText().toString());
        int numWeightp = Integer.parseInt(newWeightp.getText().toString());

        //add checks to make sure the values are there

        reply.putExtra(SPOOL_ID_PRINT, numIDp);
        reply.putExtra(NEW_WEIGHT_PRINT, numWeightp);
        setResult(RESULT_OK, reply);

        Log.d("test","finidhed print thingy");

        finish();
    }
}
