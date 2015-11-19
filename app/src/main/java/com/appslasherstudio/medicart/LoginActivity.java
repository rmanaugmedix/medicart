package com.appslasherstudio.medicart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    EditText emailEt, passwordEt;
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ref = new Firebase("https://medicart.firebaseio.com/");
        loginBtn = (Button) findViewById(R.id.button);
        emailEt = (EditText) findViewById(R.id.emailEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt);

        final Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("userId", authData.toString());
                startActivity(intent);
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
            }
        };



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ref.authWithPassword(emailEt.getText().toString(), passwordEt.getText().toString(), authResultHandler);
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("userId", "arman");
                startActivity(intent);
            }
        });
    }
}
