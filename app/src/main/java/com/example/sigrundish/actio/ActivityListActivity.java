package com.example.sigrundish.actio;

import android.support.v4.app.Fragment;
import android.widget.EditText;

/**
 * Created by sigrundish on 25/02/2018.
 */

public class ActivityListActivity extends SingleFragmentActivity{


    @Override
    protected Fragment createFragment() {
        return new ActivityListFragment();
    }

}
