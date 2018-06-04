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
    String price;

    public Parking(LatLng latLng, String icon, String id, String name, String openNow, String placeId, String vicinity, String price) {
        this.latLng = latLng;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.openNow = openNow;
        this.placeId = placeId;
        this.vicinity = vicinity;
        this.price = price;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenNow() {
        return openNow;
    }

    public void setOpenNow(String openNow) {
        this.openNow = openNow;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
