package com.sabututexp.tutexplocation.event;

import com.sabututexp.tutexplocation.model.ResultsItem;

import java.util.List;

/**
 * Created by s on 20/11/17.
 */

public class NearbyPlacesEvent {
    private List<ResultsItem> places;

    public NearbyPlacesEvent(List<ResultsItem> places) {
        this.places = places;
    }

    public List<ResultsItem> getPlaces() {
        return places;
    }
}
