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
/** Class that handles adding a new spool
 * @author Edward Neil
 * @version 1.0
 * @since 1.0
 */
public class AddSpoolActivity extends AppCompatActivity {

    /** Represents a static string that is a key for value 'spoolName'
     */
    public static final String REPLY_NAME = "com.example.cs246team22.printingapp.NAME";

    /** Represents a static string that is a key for value 'spoolBrand'
     */
    public static final String REPLY_BRAND = "com.example.cs246team22.printingapp.BRAND";

    /** Represents a static string that is a key for value 'spoolColor'
     */
    public static final String REPLY_COLOR = "com.example.cs246team22.printingapp.COLOR";

    /** Represents a static string that is a key for value 'spoolWeight'
     */
    public static final String REPLY_WEIGHT = "com.example.cs246team22.printingapp.WEIGHT";

    /** Represents a static string that is a key for value 'spoolMaterial'
     */
    public static final String REPLY_MATERIAL = "com.example.cs246team22.printingapp.MATERIAL";

    /** Represents a static string that is default name for a logcat log
     */
    public static String TAG = "AddSpoolActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_spool_entry);

    }
    /** Adds a new spool
     * @param view View view
     */
    public void addSpool(View view){

        Log.d(TAG, "Begin adding new spool");

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
            Log.d(TAG, "Got Spool Name");
        }

        if (TextUtils.isEmpty(inputBrand.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String spoolBrand = inputBrand.getText().toString();
            replyIntent.putExtra(REPLY_BRAND, spoolBrand);
            setResult(RESULT_OK, replyIntent);
            Log.d(TAG, "Got Spool Brand");
        }

        if (TextUtils.isEmpty(inputColor.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String spoolColor = inputColor.getText().toString();
            replyIntent.putExtra(REPLY_COLOR, spoolColor);
            setResult(RESULT_OK, replyIntent);
            Log.d(TAG, "Got Spool Color");
        }

        if (TextUtils.isEmpty(inputWeight.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            int spoolWeight = Integer.parseInt(inputWeight.getText().toString());
            replyIntent.putExtra(REPLY_WEIGHT, spoolWeight);
            setResult(RESULT_OK, replyIntent);
            Log.d(TAG, "Got Spool Weight");
        }

        //No if-else because it will never be empty
        String spoolMaterial = inputMaterial.getSelectedItem().toString();
        replyIntent.putExtra(REPLY_MATERIAL, spoolMaterial);
        setResult(RESULT_OK, replyIntent);
        Log.d(TAG, "Got Spool Material");



        Log.d(TAG, "Finished adding new spool");
        finish();

    }

    /** Returns to MainActivity on Back button press
     * @author Aiden
     * @since 1.0
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
