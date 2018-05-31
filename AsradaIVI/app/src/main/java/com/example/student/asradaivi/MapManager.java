package com.example.student.asradaivi;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

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

    // Add parkingLot Marker

    //
    public void carLocationChanged() {
        if (!mapView) {
            return;
        }

        m.updateMap(carSimulation.getLatLng());

        Log.d(TAG, "carLocationChanged");
        LatLng latLng = carSimulation.getLatLng();


        //Request URL
        try {

            JSONParser jsonParser = new JSONParser();
            int radius = 20000;
            String type = "parking";
            String language = "ko";
            String params = "location=" + latLng.latitude + "," + latLng.longitude
                    + "&radius=" + radius
                    + "&type=" + type
                    + "&language=" + language
                    + "&key="
                    + ApplicationData.location_key;

            // LocationChanged!!!
            startAnimation();

            // Request URL
            jsonParser.makeHttpRequest(this, ApplicationData.location_url, params, "POST");

        } catch (Exception e) {
            e.printStackTrace();
        }
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

/*    public void updateMap(GoogleMap mMap) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng( carSimulation.getLatLng()));
        mapController.updateMap(mMap, carSimulation.getLatLng());
    }*/
}
