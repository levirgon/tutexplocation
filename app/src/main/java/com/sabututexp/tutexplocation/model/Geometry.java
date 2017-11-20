package com.sabututexp.tutexplocation.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by s on 20/11/17.
 */

public class Geometry {
    @SerializedName("location")
    private PlaceLocation mPlaceLocation;

    public void setPlaceLocation(PlaceLocation placeLocation) {
        this.mPlaceLocation = placeLocation;
    }

    public PlaceLocation getPlaceLocation() {
        return mPlaceLocation;
    }

    @Override
    public String toString() {
        return
                "Geometry{" +
                        "mPlaceLocation = '" + mPlaceLocation + '\'' +
                        "}";
    }
}
