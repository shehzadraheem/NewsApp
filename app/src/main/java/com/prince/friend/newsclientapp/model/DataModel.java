package com.prince.friend.newsclientapp.model;

import com.huawei.hms.maps.model.LatLng;

public class DataModel {

    LatLng latlng;
    String title;

    public DataModel(LatLng latlng, String title) {
        this.latlng = latlng;
        this.title = title;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public String getTitle() {
        return title;
    }
}
