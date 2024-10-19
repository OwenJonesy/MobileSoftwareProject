package com.example.drivingschoolapp;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Activity for displaying a map with markers for user and instructor locations
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Callback method invoked when the map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Replace YOUR_API_KEY with your actual API key
        // Replace the latitude and longitude values with your actual location and instructor locations
        LatLng myLocation = new LatLng(53.2871, -6.3496);
        LatLng instructor1 = new LatLng(53.2899, -6.2426);
        LatLng instructor2 = new LatLng(53.3594, -6.3966);

        // Add markers for your location and instructors
        googleMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
        googleMap.addMarker(new MarkerOptions().position(instructor1).title("Paul Johnson"));
        googleMap.addMarker(new MarkerOptions().position(instructor2).title("Tom Smith"));

        // Move the camera to your location and zoom in
        float zoomLevel = 10.0f; // You can set this value according to your needs
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, zoomLevel));
    }
}
