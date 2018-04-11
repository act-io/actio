package com.example.sigrundish.actio;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ActivityInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    Geocoder geo;
    List<Address> loc;
    int nrAtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final TextView title = findViewById(R.id.tvActivity);
        final TextView description = findViewById(R.id.tvInfo);
        final TextView locale = findViewById(R.id.tvLocation);
        final TextView attendees = findViewById(R.id.tvAttendees);


        title.setText(getIntent().getStringExtra("Title"));
        description.setText(getIntent().getStringExtra("Description"));

        String activityId = getIntent().getStringExtra("id");
        String location = getIntent().getStringExtra("Location");

        locale.setText(location);

        setAttendees(activityId, attendees);



        geo = new Geocoder(this);
        //leita af location á íslandi með streng
        try {
            loc = geo.getFromLocationName(location,1,63.0,-24.7,67.0,-12.3);
        } catch (IOException e) {
            e.printStackTrace();
        }



        initMap();

    }

    public void initMap() {
        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        if(!loc.isEmpty()){
            LatLng location = new LatLng(loc.get(0).getLatitude(), loc.get(0).getLongitude());
            mGoogleMap.addMarker(new MarkerOptions().position(location).title("Your activity is here"));
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f));

        }


    }
    private void setAttendees(String id, final TextView tv){

        String url = "https://actio-server.herokuapp.com/usersattendingactivity/activity/" + id;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                           JSONObject jsonObject = response.getJSONObject("data");
                           nrAtt = Integer.parseInt(jsonObject.getString("count"));
                           tv.setText("Attendees: " + nrAtt);


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


}
