package com.example.sigrundish.actio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://actio-server.herokuapp.com/users";

        queue.start();
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("takki");

                HashMap<String, String> params = new HashMap<String,String>();
                params.put("age", etAge.getText().toString()); // the entered data as the body.
                params.put("name", etName.getText().toString()); // the entered data as the body.
                params.put("username", etUsername.getText().toString()); // the entered data as the body.
                params.put("password", etPassword.getText().toString()); // the entered data as the body.
                JsonObjectRequest jsObjRequest = new
                        JsonObjectRequest(Request.Method.POST,
                        url,
                        new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // DisplayText.setText(response.getString("message"));
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

                //Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                //RegisterActivity.this.startActivity(LoginIntent);

            }
    }


