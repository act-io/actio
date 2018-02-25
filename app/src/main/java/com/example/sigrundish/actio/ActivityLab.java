package com.example.sigrundish.actio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Klasi sem inniheldur mörg innbrot. Klasinn er factory klasi.
 */

public class ActivityLab {
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
        // Búum til dummy gögn
        for (int i=0; i<100; i++) {
            Activity activity = new Activity();
            activity.setTitle("Activity title#" + i);
            activity.setDescription("Activity description#" + i);
            activity.setLocation("Activity location#" + i);
            mActivities.add(activity);
        }
    }
    public List<Activity> getActivities() {
        return mActivities;
    }

    public Activity getActivity (UUID id) {
        for (Activity activity: mActivities) {
            if (activity.getId().equals(id)) {
                return activity;
            }
        }
        return null;
    }
}