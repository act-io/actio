package com.example.sigrundish.actio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity class for Activity
 * Created by teymi 8 on 25/02/2018.
 */

public class ActivityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new ActivityFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment) // FrameLayout
                    .commit();
        }
    }
}
