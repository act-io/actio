package com.example.sigrundish.actio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView tvLoginValidation = (TextView) findViewById(R.id.tvLoginValidation);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://actio-server.herokuapp.com/login";

        queue.start();
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> params = new HashMap<String,String>();
                params.put("username", etUsername.getText().toString()); // the entered data as the body.
                params.put("password", etPassword.getText().toString()); // the entered data as the body.
                final String username = params.get("username");
                JsonObjectRequest jsObjRequest = new
                        JsonObjectRequest(Request.Method.POST,
                        url,
                        new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if(response.getBoolean("login")){
                                        JSONObject jsonUser = response.getJSONObject("data");
                                        User user = new User();
                                        user.setId(jsonUser.getInt("id"));
                                        user.setUsername(jsonUser.getString("username"));
                                        user.setPassword(jsonUser.getString("password"));
                                        user.setName(jsonUser.getString("name"));
                                        user.setAge(jsonUser.getString("age"));
                                        Intent userAreaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                        //Send the user to next activity.
                                        userAreaIntent.putExtra("user", user);
                                        LoginActivity.this.startActivity(userAreaIntent);
                                    }
                                    else{
                                        JSONArray jsonValidation = response.getJSONArray("validation");
                                        if (jsonValidation != null) {
                                            ArrayList<String> validationArray = new ArrayList<>();
                                            JSONObject json = jsonValidation.getJSONObject(0);
                                            tvLoginValidation.setText(json.getString("error"));
                                        }
                                    }
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
                queue.add(jsObjRequest);

            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                finish();
            }
        });
    }
}
