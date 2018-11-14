package com.example.cs246team22.printingapp;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddSpoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spool);
    }

    public void addSpool(View view) {

        EditText editText = findViewById(R.id.editManufacturer);
        String manufacturer = editText.getEditableText().toString();

        SharedPreferences sharedPref = this.getSharedPreferences(
                "com.example.cs246team22.printingapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("MANUFACTURER_NAME", manufacturer);
        editor.apply();

        Toast.makeText(this, manufacturer, Toast.LENGTH_SHORT).show();
        AddSpoolActivity.this.finish();
    }


}
