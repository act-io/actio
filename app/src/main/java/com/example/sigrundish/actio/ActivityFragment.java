package com.example.sigrundish.actio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by sigrundish on 25.2.2018.
 */

public class ActivityFragment  extends Fragment {
    private Activity mActivity;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private EditText mLocationField;
    private Button bCreateActivity;
    final String url = "http://actio-server.herokuapp.com/activities";

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = new Activity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity, container, false);

        mTitleField = (EditText) v.findViewById(R.id.activity_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (
                    CharSequence s, int start, int count, int after) {
                // blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mActivity.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Blank
            }

        });

        mDescriptionField = (EditText) v.findViewById(R.id.activity_description);
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (
                    CharSequence s, int start, int count, int after) {
                // blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mActivity.setDescription(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Blank
            }

        });

        mLocationField = (EditText) v.findViewById(R.id.activity_location);
        mLocationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (
                    CharSequence s, int start, int count, int after) {
                // blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mActivity.setLocation(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Blank
            }

        });

        bCreateActivity = (Button) v.findViewById(R.id.bCreateActivity);
        bCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.start();
                HashMap<String, String> params = new HashMap<String,String>();
                params.put("title",mActivity.getTitle()); // the entered data as the body.
                params.put("description", mActivity.getDescription()); // the entered data as the body.
                params.put("location", mActivity.getLocation()); // the entered data as the body.
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
        return v;
    }


}
