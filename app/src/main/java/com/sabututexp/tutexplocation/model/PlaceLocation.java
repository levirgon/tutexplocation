package com.sabututexp.tutexplocation.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by s on 20/11/17.
 */

public class PlaceLocation {
    @SerializedName("lng")
    private double lng;

    @SerializedName("lat")
    private double lat;

    public void setLng(double lng){
        this.lng = lng;
    }

    public double getLng(){
        return lng;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return lat;
    }

    @Override
    public String toString(){
        return
                "PlaceLocation{" +
                        "lng = '" + lng + '\'' +
                        ",lat = '" + lat + '\'' +
                        "}";
    }
}
