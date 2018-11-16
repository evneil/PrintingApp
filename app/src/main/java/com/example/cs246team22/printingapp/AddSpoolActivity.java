package com.example.cs246team22.printingapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddSpoolActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.cs246team22.printingapp.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_spool_entry);
    }

    public void addSpool(View view){

        Spool spool = new Spool();

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

        Intent replyIntent = new Intent();




        finish();
    }
}
