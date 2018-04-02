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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserAreaActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private User user = new User();
    private String username = "";




    @SuppressLint("ByteOrderMark")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final TextView logOutLink = (TextView) findViewById(R.id.menLogout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final Button bCreateActivity = (Button) findViewById(R.id.bCreateActivity);
        final Button bActivities = (Button) findViewById(R.id.bActivities);
        final TextView greetingUser = (TextView) findViewById(R.id.tGreetUser);
        String username = getIntent().getStringExtra("USERNAME");
        setUser(username);
        System.out.println("user.getName()" + user.getName());
        greetingUser.setText(user.getName());


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
                UserAreaActivity.this.startActivity(activitiesIntent);
            }
        });


    }

    private void setUser(String username) {
        System.out.println("username in getUser" + username);
        String url = "https://actio-server.herokuapp.com/users/" + username;
        RequestQueue queue = Volley.newRequestQueue(this);

        //Request list of activities stored in the database table 'activities'.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           JSONObject jsonUser = response.getJSONObject("data");
                           user.setId(Integer.parseInt(jsonUser.getString("id")));
                           user.setUsername(jsonUser.getString("username"));
                           user.setPassword(jsonUser.getString("password"));
                           user.setName(jsonUser.getString("name"));
                           user.setAge(jsonUser.getString("age"));
                            final TextView greetingUser = (TextView) findViewById(R.id.tGreetUser);
                            greetingUser.setText("Hey " + user.getName() + "!");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // DisplayText.setText("That didn't work!");
            }
        });
        queue.add(jsonObjectRequest);
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