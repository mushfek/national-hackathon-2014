package com.nationalappsbd.hackathon.namenotfound.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.nationalappsbd.hackathon.namenotfound.app.R;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Story;
import com.nationalappsbd.hackathon.namenotfound.app.service.StoryFactory;
import com.oneous.log4android.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ContentView(R.layout.activity_heatmap)
public class HeatmapActivity extends RoboFragmentActivity {
    private static final Logger log = Logger.getLogger(HeatmapActivity.class);

    private LatLng latLng = new LatLng(23, 90);

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @InjectView(R.id.addNew)
    private AddFloatingActionButton addNew;

    @InjectView(R.id.category)
    private Spinner category;

    private static final int HEAT_MAP_MODE = 1;
    private static final int MAP_POINTER = 2;

    private static int MODE = HEAT_MAP_MODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        setUpMapIfNeeded();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.category));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(spinnerArrayAdapter);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeatmapActivity.this, AddStoryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    Object object = adapterView.getItemAtPosition(i);
                    log.debug("onItemSelected() ={}, position ={}, id ={}", object, i, l);
                    filterMap(object);
                } else filterMap(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void filterMap(Object object) {
        if (MODE == HEAT_MAP_MODE) {
            if (object != null) {
                List<LatLng> latLngs = StoryFactory.getInstance().getFilteredLocation(object.toString());
                addHeatMap(latLngs);
            } else {
                addHeatMap(StoryFactory.getInstance().getLocation());
            }
        } else if (MODE == MAP_POINTER) {
            if (object != null) {
                addMapPointer(StoryFactory.getInstance().getFilteredStories(object.toString()));
            } else {
                addMapPointer(StoryFactory.getInstance().getStories());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_heatmap, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.action_heatmap) {
            if (mMap != null) {
                addHeatMap(StoryFactory.getInstance().getLocation());
                MODE = HEAT_MAP_MODE;
                category.setSelection(0);
            }
        }

        if (item.getItemId() == R.id.action_map_pointer) {
            if (mMap != null) {
                addMapPointer(StoryFactory.getInstance().getStories());
                MODE = MAP_POINTER;
                category.setSelection(0);
            }
        }

        return false;
    }

    private void addMapPointer(List<Story> stories) {
        if (mMap != null) {
            mMap.clear();

            for (Story story : stories) {
                mMap.addMarker(new MarkerOptions()
                        .position(story.getLatLng())
                        .title(story.getPledge()))
                        .setSnippet(story.getId() + "# " + story.getStory());

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                changeZoomButton();
                setUpMap();
                addHeatMap(StoryFactory.getInstance().getLocation());

                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String snipet = marker.getSnippet();
                        Long id = Long.parseLong(snipet.substring(0, (snipet.indexOf("#"))));
                        log.debug("onMarkerClick() , id={}", id);

                        Intent intent = new Intent(HeatmapActivity.this, StoryDetailsActivity.class);
                        intent.putExtra(StoryDetailsActivity.STORY_DETAILS_KEY, id);
                        startActivity(intent);

                    }
                });

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        return false;
                    }
                });
            }
        }
    }

    //TODO this is totally hacking, we have to find some other way
    private void changeZoomButton() {
        // Find ZoomControl view
        View zoomControls = getSupportFragmentManager().findFragmentById(R.id.map).getView().findViewById(0x1);

        if (zoomControls != null && zoomControls.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            // ZoomControl is inside of RelativeLayout
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) zoomControls.getLayoutParams();

            // Align it to - parent top|left
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            // Update margins, set to 10dp
            final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics());
            params.setMargins(margin, margin, margin, margin);
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        log.debug("setupMap()");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
    }

    private void addHeatMap(List<LatLng> list) {
        log.debug("addHeatMap()");

        mMap.clear();

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        if (list != null && list.size() > 0) {
            HeatmapTileProvider heatmapTileProvider = new HeatmapTileProvider.Builder()
                    .data(list)
                    .build();
            // Add a tile overlay to the map, using the heat map tile provider.
            mMap.addTileOverlay(new TileOverlayOptions().tileProvider(heatmapTileProvider));
        }
    }

    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }
}
