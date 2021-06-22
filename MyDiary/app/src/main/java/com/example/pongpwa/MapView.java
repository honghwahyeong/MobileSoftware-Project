package com.example.pongpwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapView extends AppCompatActivity
    implements OnMapReadyCallback {

    double lat;
    double lon;
    String location;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        Intent intent = getIntent();

        lat = intent.getDoubleExtra("lat",lat);
        lon = intent.getDoubleExtra("lon",lon);
        location = intent.getStringExtra("location");

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        LatLng PLACELOCATION = new LatLng(lat,lon);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(PLACELOCATION);
        markerOptions.title(location);
        markerOptions.snippet("위도 : " + lat + " " + "경도 : " + lon);
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PLACELOCATION,15));

    }
}