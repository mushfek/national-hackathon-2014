package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Story;
import com.nationalappsbd.hackathon.namenotfound.app.service.StoryFactory;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboFragmentActivity;

public class AddStoryLocationActivity extends RoboFragmentActivity implements LocationListener {
    private Logger log = Logger.getLogger(AddStoryLocationActivity.class);

    private Story story;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LatLng lastClickedLocation;

    private Location location;
    private int noOfTimesLocationUpdated;

    private static final int MAX_NO_OF_LOCATION_UPDATES = 2;

    private LocationManager locationManager;

    private LatLng LATITUDE_LONGITUDE_BANGLADESH = new LatLng(23, 90);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.debug("onCreate()");

        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_location_picker);
        story = (Story) getIntent().getExtras().getSerializable(AddStoryActivity.STORY_KEY);
        Toast.makeText(this, getString(R.string.map_loading_wait_help_test), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.debug("onResume()");

        setUpMapIfNeeded();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, this);
    }

    private void setUpMapIfNeeded() {
        log.debug("setUpMapIfNeeded()");

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.

            if (mMap != null) {
                setUpMap(LATITUDE_LONGITUDE_BANGLADESH);

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        lastClickedLocation = latLng;
                        putMarker();
                    }
                });
            }
        }
    }

    private void setUpMap(LatLng location) {
        log.debug("setUpMap()");

        if (location != null) {
            log.debug("Lets move to my location");
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 8));
        }
    }

    private void putMarker() {
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(lastClickedLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            story.setLatitude(lastClickedLocation.latitude);
            story.setLongitude(lastClickedLocation.longitude);

            StoryFactory.getInstance().addStory(story);
            Intent intent = new Intent(this, HeatmapActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onLocationChanged(Location newLocation) {

        if (newLocation != null) {
            log.info("onLocationChanged. longitude={}, latitude={}, provider={}",
                    newLocation.getLongitude(), newLocation.getLatitude(), newLocation.getProvider());

            noOfTimesLocationUpdated++;

            location = newLocation;

            if (noOfTimesLocationUpdated >= MAX_NO_OF_LOCATION_UPDATES) {
                locationManager.removeUpdates(this);
                setUpMap(new LatLng(location.getLatitude(), location.getLatitude()));
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
