package com.example.student.asradaivi;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class Parking {
    LatLng latLng;
    String icon;
    String id;
    String name;
    String openNow;
    String placeId;
    String vicinity;

    public Parking(LatLng latLng, String icon, String id, String name, String openNow, String placeId, String vicinity) {
        this.latLng = latLng;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.openNow = openNow;
        this.placeId = placeId;
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "latLng=" + latLng +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", openNow='" + openNow + '\'' +
                ", placeId='" + placeId + '\'' +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}
