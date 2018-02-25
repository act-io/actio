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

/**
 * Created by sigrundish on 25.2.2018.
 */

public class ActivityFragment  extends Fragment {
    private Activity mActivity;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private EditText mLocationField;
    private Button bCreateActivity;

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
                //To do: Setja activity í töflu
                System.out.println("Activity created!");
                System.out.println("Titill: " + mActivity.getTitle());
                System.out.println("Description: " + mActivity.getDescription());
                System.out.println("Location: " + mActivity.getLocation());
            }
        });
        return v;
    }


}
