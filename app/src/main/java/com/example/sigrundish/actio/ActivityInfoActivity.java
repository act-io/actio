package com.example.sigrundish.actio;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ActivityInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    Geocoder geo;
    List<Address> loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final TextView title = findViewById(R.id.tvActivity);
        final TextView description = findViewById(R.id.tvInfo);


        title.setText(getIntent().getStringExtra("Title"));
        description.setText(getIntent().getStringExtra("Description"));
        String location = getIntent().getStringExtra("Location");

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

}
