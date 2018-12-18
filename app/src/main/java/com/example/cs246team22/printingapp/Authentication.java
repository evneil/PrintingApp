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
 * Authentication activity that registers the button presses and passes the information
 * back. It can handle registration, login, and deletion of the user.
 *
 * @author Aiden
 * @version 1.0
 * @since 12/17/18
 */
public class Authentication extends AppCompatActivity {

    public static final String AUTH_NAME  = "com.example.cs246team22.printingapp.AUTH_NAME";
    public static final String AUTH_EMAIL = "com.example.cs246team22.printingapp.AUTH_EMAIL";
    public static final String AUTH_PASS  = "com.example.cs246team22.printingapp.AUTH_PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    public void confirmRegister(View view) {
        Intent reply = new Intent();

        EditText name  = (EditText) findViewById(R.id.nameText);
        EditText email = (EditText) findViewById(R.id.emailText);
        EditText pass  = (EditText) findViewById(R.id.passText);

        if (TextUtils.isEmpty(name.getText().toString().trim())
                || TextUtils.isEmpty(email.getText().toString().trim())
                || TextUtils.isEmpty(pass.getText().toString().trim())) {

            Toast toast = Toast.makeText(this, "All fields required to register", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            String stringName  = name.getText().toString().trim();
            String stringEmail = email.getText().toString().trim();
            String stringPass  = pass.getText().toString().trim();

            reply.putExtra(AUTH_NAME, stringName);
            reply.putExtra(AUTH_EMAIL, stringEmail);
            reply.putExtra(AUTH_PASS, stringPass);
            setResult(RESULT_OK, reply);

            Log.d("test", "collected strings: register");
            Log.d("test", stringName);
            Log.d("test", stringEmail);
            Log.d("test", stringPass);


            finish();
        }

    }

    public void confirmLogin(View view) {
        Intent reply = new Intent();

        EditText name  = (EditText) findViewById(R.id.nameText);
        EditText email = (EditText) findViewById(R.id.emailText);
        EditText pass  = (EditText) findViewById(R.id.passText);

        if (TextUtils.isEmpty(name.getText().toString().trim())
                && TextUtils.isEmpty(email.getText().toString().trim())) {

            Toast toast = Toast.makeText(this, "Must have name or email to login", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            String stringName  = name.getText().toString().trim();
            String stringEmail = email.getText().toString().trim();
            String stringPass  = pass.getText().toString().trim();

            reply.putExtra(AUTH_NAME, stringName);
            reply.putExtra(AUTH_EMAIL, stringEmail);
            reply.putExtra(AUTH_PASS, stringPass);
            setResult(RESULT_OK, reply);

            Log.d("test", "collected strings: login");
            Log.d("test", stringName);
            Log.d("test", stringEmail);
            Log.d("test", stringPass);

            finish();
        }
    }

    public void onConfirmDelete(View view) {
        Intent reply = new Intent();
        reply.putExtra(AUTH_NAME, "nothing");
        reply.putExtra(AUTH_EMAIL, "nothing");
        reply.putExtra(AUTH_PASS, "nothing");
        setResult(RESULT_OK, reply);

        Log.d("test", "deleting I guess");

        finish();
    }

}