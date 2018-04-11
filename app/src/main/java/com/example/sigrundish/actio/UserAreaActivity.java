package com.example.sigrundish.actio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private User user = new User();
    private String username;



    @SuppressLint("ByteOrderMark")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final TextView logOutLink = (TextView) findViewById(R.id.menLogout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final Button bCreateActivity = (Button) findViewById(R.id.bCreateActivity);
        final Button bActivities = (Button) findViewById(R.id.bActivities);
        final Button bAttendedActivities = (Button) findViewById(R.id.bAttendedActivities);
        final TextView greetingUser = (TextView) findViewById(R.id.tGreetUser);
        user = (User)getIntent().getSerializableExtra("user");
        greetingUser.setText("Hey " + user.getName()+ "!");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        NavigationView logOutView = findViewById(R.id.menLogout);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent mainIntent = new Intent(UserAreaActivity.this, MainActivity.class);
                        UserAreaActivity.this.startActivity(mainIntent);
                        finish();
                        return true;
                    }
                });
        bCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createActivityIntent = new Intent(UserAreaActivity.this, ActivityActivity.class);
                UserAreaActivity.this.startActivity(createActivityIntent);
            }
        });

        bActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activitiesIntent = new Intent(UserAreaActivity.this, ActivityListActivity.class);
                //Sending user to next actvity
                activitiesIntent.putExtra("user", user);
                activitiesIntent.putExtra("ONLY_ATTENDED", "false");
                UserAreaActivity.this.startActivity(activitiesIntent);
            }
        });

        bAttendedActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activitiesIntent = new Intent(UserAreaActivity.this, ActivityListActivity.class);
                //Sending user to next actvity
                activitiesIntent.putExtra("user", user);
                activitiesIntent.putExtra("ONLY_ATTENDED", "true");
                UserAreaActivity.this.startActivity(activitiesIntent);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}