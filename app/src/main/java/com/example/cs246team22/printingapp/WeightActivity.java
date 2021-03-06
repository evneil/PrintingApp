package com.example.cs246team22.printingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Weight refers to the weight if the spool as you see on a measuring scale. This should be
 * used to set the weight of the spool rather than changing it from it's current value.
 *
 * @author Aiden
 * @version 1.0
 * @since 12/17/18
 */
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
        if(TextUtils.isEmpty(id.getText().toString().trim()) || TextUtils.isEmpty(newWeight.getText().toString().trim())) {
            Toast toast = Toast.makeText(this,"Field cannot be empty",Toast.LENGTH_SHORT);
            toast.show();}
        else {
            int numID = Integer.parseInt(id.getText().toString());
            int numWeight = Integer.parseInt(newWeight.getText().toString());

            reply.putExtra(SPOOL_ID, numID);
            reply.putExtra(NEW_WEIGHT, numWeight);
            setResult(RESULT_OK, reply);

            Log.d("test", "Finished collecting weight");

            finish();
        }
    }


    // Overrides the Android back button to ensure it goes back to the main activity when pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
