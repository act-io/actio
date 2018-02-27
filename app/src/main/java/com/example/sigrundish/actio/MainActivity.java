package com.example.sigrundish.actio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    //Your key is a public constant EXTRA_MESSAGE because the next activity uses the key to retrieve the text value.
    //It's a good practice to define keys for intent extras using your app's package name as a prefix.
    public static final String EXTRA_MESSAGE = "com.example.sigrundish.actio.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bCreateActivity = (Button) findViewById(R.id.bCreateActivity);
        final Button bActivities = (Button) findViewById(R.id.bActivities);



        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);

            }
        });

        bCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createActivityIntent = new Intent(MainActivity.this, ActivityActivity.class);
                MainActivity.this.startActivity(createActivityIntent);
            }
        });

        bActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activitiesIntent = new Intent(MainActivity.this, ActivityListActivity.class);
                MainActivity.this.startActivity(activitiesIntent);
            }
        });


    }



}
