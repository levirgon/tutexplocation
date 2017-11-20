package com.sabututexp.tutexplocation;

import android.app.ProgressDialog;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sabututexp.tutexplocation.Adapter.PlacesAdapter;
import com.sabututexp.tutexplocation.Retrofit.PlaceServiceProvider;
import com.sabututexp.tutexplocation.event.ErrorEvent;
import com.sabututexp.tutexplocation.event.NearbyPlacesEvent;
import com.sabututexp.tutexplocation.model.ResultsItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mPlacesList;
    private LinearLayoutManager linearLayoutManager;
    private PlacesAdapter mPlacesAdapter;
    private Location mCurrentLocation;
    private ProgressDialog progressDialog;
    private PlaceServiceProvider mServiceProvider;
    //private final double RADIUS = 1000;
    private FloatingActionButton locationButton;
    private LinearLayout mLoadingLayout;
    private TextView loadinTextView,notificationTextView;
    private ImageView pointingIcon;
    private String catType,lat,lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            catType = bundle.getString("categoryText");
        }
        lat = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Latitude", "No Latitude Value Stored");

        lon = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Longitude", "No Longitude Value Stored");
        mCurrentLocation = new Location("");
        mCurrentLocation.setLatitude(Double.parseDouble(lat));
        mCurrentLocation.setLongitude(Double.parseDouble(lon));

        initiate();

    }

    private void initiate() {
        loadinTextView = findViewById(R.id.loading_text);
       /* notificationTextView = findViewById(R.id.notif_text);
        pointingIcon = findViewById(R.id.pointing);*/
        mLoadingLayout = findViewById(R.id.loading_group);
        mLoadingLayout.setVisibility(View.GONE);
        setUpPlacesList();
        mServiceProvider = new PlaceServiceProvider();
        progressDialog = new ProgressDialog(this);
        onItemSelected(mCurrentLocation,catType);
    }

    private void setUpPlacesList() {

        mPlacesList = findViewById(R.id.nearby_places_list);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPlacesList.setLayoutManager(linearLayoutManager);
        mPlacesList.setItemAnimator(new DefaultItemAnimator());


        if (mPlacesAdapter == null) {
            mPlacesAdapter = new PlacesAdapter(this);
            mPlacesList.setAdapter(mPlacesAdapter);
        } else {
            mPlacesList.setAdapter(mPlacesAdapter);
        }
    }

    public void onItemSelected(Location loc, String text) {
        mPlacesAdapter.setCurrentLocation(loc);
        mLoadingLayout.setVisibility(View.VISIBLE);
            /*notificationTextView.setVisibility(View.GONE);
            pointingIcon.setVisibility(View.GONE);*/
        loadinTextView.setText("Loading Places");
        mServiceProvider.requestPlaces(loc, "distance", text);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNearbyPlacesEvent(NearbyPlacesEvent event) {
        mLoadingLayout.setVisibility(View.GONE);
        List<ResultsItem> places = event.getPlaces();
        mPlacesAdapter.clear();
        mPlacesAdapter.addAll(places);
        //add the data to a list
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        String errorMessage = event.getErrorMessage();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void onPlaceSelected(ResultsItem place) {
        double lat = place.getGeometry().getPlaceLocation().getLat();
        double lng = place.getGeometry().getPlaceLocation().getLng();

        Location destination = new Location("");
        destination.setLatitude(lat);
        destination.setLongitude(lng);


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
