package com.example.student.asradaivi;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class CarSimulation extends Thread {
    private String TAG = String.format("%20s", "CarSimulation :: ");
    private MapManager mapManager;
    private LatLng latLng;
    private LatLng latLngLast;
    private boolean flag;
    private boolean flagView;

    Random rand;
    private int[][] dir = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};

    public CarSimulation(MapManager mapManager) {
        Log.d(TAG, "Construct");
        this.mapManager = mapManager;

        latLng = ApplicationData.currentLatLng;
        latLngLast = new LatLng(0,0);
        rand = new Random();
        flag = true;
        flagView = false;
    }

    public boolean isFlagView() {
        return flagView;
    }

    public void setFlagView(boolean flagView) {
        this.flagView = flagView;
    }

    @Override
    public void run() {
        int a = 0;
        while (flag) {
            if (flagView) {
                int d = rand.nextInt(4);
                Log.d(TAG, " " + d);

                latLng = new LatLng(latLng.latitude
                        + (dir[d][0]) * (Math.round(rand.nextDouble() * 100d) / 300000d),
                        latLng.longitude
                                + (dir[d][1]) * Math.round(rand.nextDouble() * 100d) / 300000d);

                // Location Changed !
                if(a > 2) {
                    mapManager.carLocationChanged(true);
                    a = 0;
                }
                else {
                    mapManager.carLocationChanged(false);
                }
            }
            try {
                Thread.sleep(1000);
                a++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getDistance() {
        double res = (latLng.latitude - latLngLast.latitude) * (latLng.latitude - latLngLast.latitude)
                + (latLng.longitude - latLngLast.longitude) * (latLng.longitude - latLngLast.longitude);

        Log.d(TAG, "distance !!!" + Math.sqrt(res));
        return Math.sqrt(res);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLngLast() {
        return latLngLast;
    }

    public void setLatLngLast(LatLng latLngLast) {
        this.latLngLast = latLngLast;
    }

    public void setLatLngLasttoCur() {
        latLngLast = latLng;
    }

    public void stopCarSimulation() {
        flag = false;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
