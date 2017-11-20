package com.sabututexp.tutexplocation.Retrofit;

import android.util.Log;


import com.sabututexp.tutexplocation.ApiInterface.PlacesService;
import com.sabututexp.tutexplocation.event.ErrorEvent;
import com.sabututexp.tutexplocation.event.NearbyPlacesEvent;
import com.sabututexp.tutexplocation.model.Places;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by s on 20/11/17.
 */

public class PlaceServiceProvider {
    private static final PlacesService mService = ServiceGenerator.createService(PlacesService.class);
    private static final String WEB_SERVICE_KEY = "AIzaSyBDAw4dPVVbyQdKBTwL7VL_gO8nFEA5eC0";
    //AIzaSyBDAw4dPVVbyQdKBTwL7VL_gO8nFEA5eC0

    private static final String TAG = "PlaceServiceProvider";

    public void requestPlaces(android.location.Location location, String sortBy, String placeType) {

        String locationText = location.getLatitude() + "," + location.getLongitude();
        Call<Places> forecastCall = mService.getPlacesByOrder(WEB_SERVICE_KEY, locationText, sortBy, placeType);

        forecastCall.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                if (response.isSuccessful()) {
                    Places places = response.body();
                    if (places != null) {
                        EventBus.getDefault().post(new NearbyPlacesEvent(places.getResults()));
                    }
                    Log.d(TAG, "onResponse: Successful :" + places.toString());
                } else {
                    try {
                        Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }
}
