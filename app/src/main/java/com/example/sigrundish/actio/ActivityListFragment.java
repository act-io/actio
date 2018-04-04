package com.example.sigrundish.actio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sigrundish on 25/02/2018.
 */

public class ActivityListFragment extends Fragment {
    private RecyclerView mActivityRecyclerView;
    private ActivityAdapter mAdapter;
    private ActivityLab activityLab ;
    private List<Activity> activities = new ArrayList<>();
    final String url ="https://actio-server.herokuapp.com/activities";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);
        EditText etsearchActivity = view.findViewById(R.id.etSearchActivity);

        etsearchActivity.clearFocus();

        etsearchActivity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()==0){
                    mAdapter.resetList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());


            }
        });
        mActivityRecyclerView = (RecyclerView) view.findViewById(R.id.activity_recycler_view);
        mActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }
    private void filter(String text1){
        ArrayList<Activity> filteredList = new ArrayList<>();

        for (Activity item : activities) {
            if (item.getTitle().toLowerCase().contains(text1.toLowerCase())) {
                filteredList.add(item);

            }
            else if(item.getLocation().toLowerCase().contains(text1.toLowerCase())){
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }


    private void updateUI() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        activityLab = ActivityLab.get(getActivity());

        //Request list of activities stored in the database table 'activities'.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            activities = activityLab.jsonArrayToActivityList(response.getJSONArray("data"));
                            mAdapter = new ActivityAdapter(activities);
                            mActivityRecyclerView.setAdapter(mAdapter);

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
            Intent infoIntent = new Intent(getActivity(), ActivityInfoActivity.class);
            infoIntent.putExtra("Title", mActivity.getTitle());
            infoIntent.putExtra("Description", mActivity.getDescription());
            infoIntent.putExtra("Location", mActivity.getLocation());
            infoIntent.putExtra("id", mActivity.getId());
            startActivity(infoIntent);


        }

    }
    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {
        private List<Activity> mActivities;
        private List<Activity> mActivitiesOriginal;
        public ActivityAdapter(List<Activity> activities) {
            mActivities = activities;
            mActivitiesOriginal = activities;
        }
        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ActivityHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder ( ActivityHolder holder,  int position) {
            Activity activity = mActivities.get(position);
            holder.bind(activity);
            holder.setIsRecyclable(false);
        }
        @Override
        public int getItemCount() {
            return mActivities.size();
        }


        public void filterList(ArrayList<Activity> filteredList) {
            mActivities = filteredList;
            notifyDataSetChanged();
        }
        public void resetList(){
            mActivities = mActivitiesOriginal;
            notifyDataSetChanged();
        }
    }
}
