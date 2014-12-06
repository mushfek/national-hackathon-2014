package com.nationalappsbd.hackathon.namenotfound.app.heatmap.activity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
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
import com.nationalappsbd.hackathon.namenotfound.app.heatmap.domain.Story;
import com.oneous.log4android.Logger;
import roboguice.activity.RoboFragmentActivity;

public class LocationPickerActivity extends RoboFragmentActivity implements LocationListener {
    private Logger log = Logger.getLogger(LocationPickerActivity.class);

    private Story story;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LatLng lastClickedLocation;

    private Location location;
    private int noOfTimesLocationUpdated;

    private static final int MAX_NO_OF_LOCATION_UPDATES = 2;

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        log.debug("onCreate()");

        setContentView(R.layout.activity_location_picker);
        story = (Story) getIntent().getExtras().getSerializable(StoryActivity.STORY_KEY);

        //setUpMapIfNeeded();


        Toast.makeText(this, "Wait a while to load your current position in the map", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.debug("onResume()");

        setUpMapIfNeeded();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // Criteria criteria = new Criteria();
        // criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // String provider = locationManager.getBestProvider(criteria, true);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 0, this);
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

    private void setUpMap() {
        log.debug("setUpMap()");

        if (location != null) {
            log.debug("Lets move to my location");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
        }
    }

    private void putMarker() {
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(lastClickedLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_narker)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

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
                setUpMap();
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