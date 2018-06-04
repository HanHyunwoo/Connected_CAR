package com.example.student.asradaivi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arsy.maps_library.MapRipple;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapManager {
    private String TAG = String.format("%20s", "MapManager :: ");
    MainActivity m;
    MapController mapController;
    CarSimulation carSimulation;
    private boolean mapView;

    public MapManager(MainActivity m, GoogleMap mMap) {
        Log.d(TAG, "Construct");
        this.m = m;
        mapView = false;
        mapController = new MapController(mMap, this);
        carSimulation = new CarSimulation(this);
        carSimulation.start();
    }

    public boolean isMapView() {
        return mapView;
    }

    public void setMapView(boolean mapView) {
        this.mapView = mapView;
        carSimulation.setFlagView(mapView);
    }

    public void carLocationChanged(boolean moveflag) {
        if (!mapView) {
            return;
        }

        if (carSimulation.getDistance() < ApplicationData.updateDistance) {
            if(moveflag)
                m.moveMap(carSimulation.getLatLng());
            else
                m.addCurrentLoc(carSimulation.getLatLng());
            return;
        }

        carSimulation.setLatLngLasttoCur();
        Log.d(TAG, "carLocationChanged");

        LatLng latLng = carSimulation.getLatLng();
        //Request URL
        try {
            JSONParser jsonParser = new JSONParser();
            String type = "parking";
            String language = "ko";
            String params = "location=" + latLng.latitude + "," + latLng.longitude
                    + "&radius=" + ApplicationData.locationRadius
                    + "&type=" + type
                    + "&language=" + language
                    + "&key="
                    + ApplicationData.location_key;

            // Request URL
            jsonParser.makeHttpRequest(this, ApplicationData.location_url, params, "POST");

            m.updateMap(carSimulation.getLatLng());
        } catch (Exception e) {
            requestComplete();
            e.printStackTrace();
        }
    }

    public void requestComplete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m.updateCompleteMap();
            }
        }).start();
    }

    public void startAnimation() {

    }

    public void stopAnimation() {

    }

    public void addMarkers(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = null;
            String openNow = "";
            String price_level = "";

            try {
                jo = jsonArray.getJSONObject(i);
                JSONObject open = jo.getJSONObject("opening_hours");
                openNow = open.getString("open_now");
            } catch (JSONException e) {
                Log.d(TAG, "exception" + e.getMessage());
            }

            try {
                price_level = jo.getString("price_level");
            } catch (JSONException e) {
                Log.d(TAG, "exception" + e.getMessage());
            }

            try {
                JSONObject geo = jo.getJSONObject("geometry");
                geo = geo.getJSONObject("location");
                LatLng latLng = new LatLng(geo.getDouble("lat"), geo.getDouble("lng"));

                m.addMarker(new Parking(
                        latLng, jo.getString("icon")
                        , jo.getString("id")
                        , jo.getString("name")
                        , openNow
                        , jo.getString("place_id")
                        , jo.getString("vicinity")
                        , price_level
                ));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public LatLng getLatLng() {
        return carSimulation.getLatLng();
    }

    public void carMarkerManagement(final Marker marker) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(!mapView) {

                }

                m.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        marker.setAlpha(0.6f);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(!mapView) {

                }

                m.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        marker.setAlpha(0.3f);
                    }
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(!mapView) {

                }

                m.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        marker.remove();
                    }
                });

            }
        }).start();
    }
}
