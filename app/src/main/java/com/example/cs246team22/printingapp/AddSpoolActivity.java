package com.example.cs246team22.printingapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddSpoolActivity extends AppCompatActivity {

    public static final String REPLY_NAME = "com.example.cs246team22.printingapp.NAME";
    public static final String REPLY_BRAND = "com.example.cs246team22.printingapp.BRAND";
    public static final String REPLY_COLOR = "com.example.cs246team22.printingapp.COLOR";
    public static final String REPLY_WEIGHT = "com.example.cs246team22.printingapp.WEIGHT";
    public static final String REPLY_MATERIAL = "com.example.cs246team22.printingapp.MATERIAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_spool_entry);

    }

    public void addSpool(View view){

        Intent replyIntent = new Intent();
        EditText inputName = (EditText) findViewById(R.id.spoolName);
        EditText inputBrand = (EditText) findViewById(R.id.spoolBrand);
        EditText inputColor = (EditText) findViewById(R.id.spoolColor);
        EditText inputWeight = (EditText) findViewById(R.id.spoolWeight);
        Spinner inputMaterial = (Spinner) findViewById(R.id.spoolMaterialSpinner);



        if (TextUtils.isEmpty(inputName.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String spoolName = inputName.getText().toString();
            replyIntent.putExtra(REPLY_NAME, spoolName);
            setResult(RESULT_OK, replyIntent);
        }

        if (TextUtils.isEmpty(inputBrand.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String spoolBrand = inputBrand.getText().toString();
            replyIntent.putExtra(REPLY_BRAND, spoolBrand);
            setResult(RESULT_OK, replyIntent);
        }

        if (TextUtils.isEmpty(inputColor.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String spoolColor = inputColor.getText().toString();
            replyIntent.putExtra(REPLY_COLOR, spoolColor);
            setResult(RESULT_OK, replyIntent);
        }

        if (TextUtils.isEmpty(inputWeight.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            int spoolWeight = Integer.parseInt(inputWeight.getText().toString());
            replyIntent.putExtra(REPLY_WEIGHT, spoolWeight);
            setResult(RESULT_OK, replyIntent);
        }

            //No if-else because it will never be empty
            String spoolMaterial = inputMaterial.getSelectedItem().toString();
            replyIntent.putExtra(REPLY_MATERIAL, spoolMaterial);
            setResult(RESULT_OK, replyIntent);


            //Context context = getApplicationContext();
            //Toast.makeText(context, "Added Spool" + spoolMaterial, Toast.LENGTH_SHORT).show();

            finish();


        /*
        Spool spool = new Spool() ;

        EditText inputName = (EditText) findViewById(R.id.spoolName);
        EditText inputBrand = (EditText) findViewById(R.id.spoolBrand);
        EditText inputColor = (EditText) findViewById(R.id.spoolColor);
        EditText inputWeight = (EditText) findViewById(R.id.spoolWeight);
        Spinner inputMaterial = (Spinner) findViewById(R.id.spoolMaterialSpinner);

        String spoolName = inputName.getText().toString();
        String spoolBrand = inputBrand.getText().toString();
        String spoolColor = inputColor.getText().toString();
        int spoolWeight = Integer.parseInt(inputWeight.getText().toString());
        String spoolMaterial = inputMaterial.getSelectedItem().toString();

        spool.setSpoolName(spoolName);
        spool.setSpoolBrand(spoolBrand);
        spool.setSpoolColor(spoolColor);
        spool.setSpoolWeight(spoolWeight);
        spool.setSpoolMaterial(spoolMaterial);


        Log.d(getClass().getName(), String.format("Received intent with %s", spool.getSpoolName()));
        //Follow this template

        Toast.makeText(this, "Success! " + spool.getSpoolName()+ " by " + spool.getSpoolBrand() + "added", Toast.LENGTH_SHORT).show();

        Intent replyIntent = new Intent();

        */



        //finish();
    }
}
