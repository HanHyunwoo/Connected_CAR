package com.example.student.asradaivi;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class CarSimulation extends Thread {
    private String TAG = String.format("%20s", "CarSimulation :: ");
    private MapManager mapManager;
    private LatLng latLng;
    private boolean flag;
    private boolean flagView;
    Random rand;
    private int[][] dir = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public CarSimulation(MapManager mapManager) {
        Log.d(TAG, "Construct");
        this.mapManager = mapManager;
        latLng = new LatLng(37, 127);
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
        while (flag) {
            if (flagView) {
                int d = rand.nextInt(4);
                Log.d(TAG, " "+d);

                latLng = new LatLng(latLng.latitude
                        + (dir[d][0]) * (Math.round(rand.nextDouble() * 100d) / 100d),
                        latLng.longitude
                                + (dir[d][1]) * Math.round(rand.nextDouble() * 100d) / 100d);

                // Location Changed !
                mapManager.carLocationChanged();
            }

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
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
