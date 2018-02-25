package com.example.sigrundish.actio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sigrundish on 25/02/2018.
 */

public class ActivityListFragment extends Fragment {
    private RecyclerView mActivityRecyclerView;
    private ActivityAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);

        mActivityRecyclerView = (RecyclerView) view.findViewById(R.id.activity_recycler_view);
        mActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        ActivityLab activityLab = ActivityLab.get(getActivity());
        List<Activity> activities = activityLab.getActivities();

        mAdapter = new ActivityAdapter(activities);
        mActivityRecyclerView.setAdapter(mAdapter);
    }

    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private TextView mLocationTextView;


    private class ActivityHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private Activity mActivity;

        // itemView er public breyta í RecyclerView.ViewHolder

        public ActivityHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_activity, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.activity_title);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.activity_description);
            mLocationTextView = (TextView) itemView.findViewById(R.id.activity_location);
        }
        public void bind (Activity activity) {
            mActivity = activity;
            mTitleTextView.setText(mActivity.getTitle());
            mDescriptionTextView.setText(mActivity.getDescription());
            mLocationTextView.setText(mActivity.getLocation());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mActivity.getTitle()+" clicked!",Toast.LENGTH_SHORT)
                    .show();
        }

    }
    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {
        private List<Activity> mActivities;
        public ActivityAdapter(List<Activity> activities) {
            mActivities = activities;
        }
        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ActivityHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder (ActivityHolder holder, int position) {
            Activity activity = mActivities.get(position);
            holder.bind(activity);
        }
        @Override
        public int getItemCount() {
            return mActivities.size();
        }


    }
}
