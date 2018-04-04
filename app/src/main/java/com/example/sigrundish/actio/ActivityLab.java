package com.example.sigrundish.actio;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasi sem inniheldur mörg innbrot. Klasinn er factory klasi.
 */

public class ActivityLab  {
    private static ActivityLab sActivityLab;
    private List <Activity> mActivities;

    public static ActivityLab get (Context context) {
        if (sActivityLab == null) {
            sActivityLab = new ActivityLab (context);
        }
        return sActivityLab;
    }
    private ActivityLab (Context context) {
        mActivities = new ArrayList<>();
    }

    public List<Activity> getActivities() {
        return mActivities;
    }

    public Activity getActivity (int id) {
        for (Activity activity: mActivities) {
            if (activity.getId()==id) {
                return activity;
            }
        }
        return null;
    }

    public List<Activity> jsonArrayToActivityList(JSONArray jsonArray) throws JSONException {
        mActivities = new ArrayList<>();

        if (jsonArray != null) {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject json = jsonArray.getJSONObject(i);
                Activity activity = new Activity();
                activity.setId(json.getInt("id"));
                activity.setTitle(json.getString("title"));
                activity.setDescription(json.getString("description"));
                activity.setLocation(json.getString("location"));
                mActivities.add(activity);
            }
        }
        return mActivities;
    }


}