package com.appslasherstudio.medicart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    TextView tv1;
    Spinner qtySpn;
    private List<Item> item;
    Firebase myFirebaseRef;
    RecyclerView rv;
    CircularProgressButton btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Intent intent = this.getIntent();
        String userId = intent.getExtras().getString("userId").toString();
        tv1 = (TextView)findViewById(R.id.tv1);
        qtySpn = (Spinner) findViewById(R.id.qtySpn);
        btnTest = (CircularProgressButton) findViewById(R.id.btnWithText);
        myFirebaseRef = new Firebase("https://medicart.firebaseio.com/");
        myFirebaseRef.child("message2").setValue("Test Message");
        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.

        initializeData();
        RVAdapter adapter = new RVAdapter(item);
        rv.setAdapter(adapter);

        tv1.setText(userId);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTest.setIndeterminateProgressMode(true); // turn on indeterminate progress
                btnTest.setProgress(50);
                myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        tv1.setText((String)snapshot.getValue());
                        btnTest.setProgress(100); // set progress to 100 or -1 to indicate complete or error state
                        //btnTest.setProgress(0); // set progress to
                    }
                    @Override public void onCancelled(FirebaseError error) {

                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                /*myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        tv1.setText((Integer)snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                    }
                    @Override public void onCancelled(FirebaseError error) { }
                });*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeData() {
        item = new ArrayList<>();
        item.add(new Item("Emma Wilson", "23 years old", R.mipmap.ic_launcher));
        item.add(new Item("Lavery Maiss", "25 years old", R.mipmap.ic_launcher));
        item.add(new Item("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
    }
}
