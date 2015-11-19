package com.appslasherstudio.medicart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    Button registrationBtn, loginBtn;
    EditText emailEt, passwordEt, confirmPassEt;
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ref = new Firebase("https://medicart.firebaseio.com/");
        registrationBtn = (Button) findViewById(R.id.button);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        emailEt = (EditText) findViewById(R.id.emailEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt);
        confirmPassEt = (EditText) findViewById(R.id.confirmPasswordEt);


        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordEt.getText().toString().equals(confirmPassEt.getText().toString())) {
                    ref.createUser(emailEt.getText().toString(), passwordEt.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            System.out.println("Successfully created user account with uid: " + result.get("uid").toString());
                            Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                            intent.putExtra("userId", result.get("uid").toString());
                            startActivity(intent);

                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                        }
                    });
                }

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
