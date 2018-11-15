package com.example.cs246team22.printingapp;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddSpoolActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.spoollistsql.REPLY";

    private  EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spool);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SpoolListAdapter adapter = new SpoolListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editText = findViewById(R.id.editManufacturer);

    }

    public void addSpool(View view) {


        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(editText.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String manufacturer = editText.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, manufacturer);
            setResult(RESULT_OK, replyIntent);
        }
        finish();

        editText = findViewById(R.id.editManufacturer);
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
