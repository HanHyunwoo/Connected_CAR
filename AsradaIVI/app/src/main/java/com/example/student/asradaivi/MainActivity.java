package com.example.student.asradaivi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arsy.maps_library.MapRadar;
import com.arsy.maps_library.MapRipple;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String TAG = String.format("%20s", "MainActivity :: ");

    LinearLayout ll_menu, ll_score, ll_map;
    TextView tv_time;
    Date dt;
    SimpleDateFormat time;
    EditText et_search;
    WebView wv_search, wv_hexa;
    RelativeLayout rl_home, rl_energy;
    GoogleMap mMap;

    MapManager mapManager;
    private MapRadar mapRadar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        wv_search = findViewById(R.id.wv_search);
        wv_hexa = findViewById(R.id.wv_hexa);
        wv_search.setWebViewClient(new WebViewClient());
        wv_hexa.setWebViewClient(new WebViewClient());
        WebSettings webSettings1 = wv_search.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        WebSettings webSettings2 = wv_hexa.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        wv_search.setVisibility(View.INVISIBLE);

        rl_home = findViewById(R.id.rl_home);
        rl_home.setVisibility(View.VISIBLE);
        rl_energy = findViewById(R.id.rl_energy);
        rl_energy.setVisibility(View.INVISIBLE);

        ll_score = findViewById(R.id.ll_score);
        ll_score.setVisibility(View.INVISIBLE);

        ll_map = findViewById(R.id.ll_map);
        ll_map.setVisibility(View.INVISIBLE);

        tv_time = findViewById(R.id.tv_time);
        et_search = findViewById(R.id.et_search);
        time = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");
        new Thread(r).start();
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_time.setText(time.format(new Date()));
                    }
                });
            }
        }
    };

    public void ClickBTN(View v) {
        if (v.getId() == R.id.tv_home) {
            mapManager.setMapView(false);
            rl_home.setVisibility(View.VISIBLE);
            ll_score.setVisibility(View.INVISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            wv_search.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.tv_energy) {
            mapManager.setMapView(false);
            ll_score.setVisibility(View.INVISIBLE);
            rl_energy.setVisibility(View.VISIBLE);
            rl_home.setVisibility(View.INVISIBLE);
            wv_search.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
            wv_hexa.loadUrl("http://70.12.114.143/Server/energy.do");
        } else if (v.getId() == R.id.tv_score) {
            mapManager.setMapView(false);
            ll_score.setVisibility(View.VISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            rl_home.setVisibility(View.INVISIBLE);
            wv_search.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
            wv_hexa.loadUrl("http://70.12.114.143/Server/hexa.do");
            //DO_Score();
        } else if (v.getId() == R.id.tv_analysis) {
            if (mapManager != null)
                mapManager.setMapView(true);
            ll_map.setVisibility(View.VISIBLE);
            rl_home.setVisibility(View.INVISIBLE);
            ll_score.setVisibility(View.INVISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            wv_search.setVisibility(View.INVISIBLE);
            initializeMap(mMap);
        } else if (v.getId() == R.id.btn_search) {
            mapManager.setMapView(false);
            String search = et_search.getText().toString();
            wv_search.setVisibility(View.VISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            rl_home.setVisibility(View.INVISIBLE);
            wv_search.loadUrl("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + search);
        }
    }

    public void ClickSensorBTN(View v) {
        /*if (v.getId() == R.id.) {

        } else if (v.getId() == R.id.switch1) {

        }*/
    }

    public void DO_Score() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://70.12.114.143/Server/hexa.do");
            conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "*/*");
                conn.getResponseCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady");
        mMap = googleMap;
        mapManager = new MapManager(this, googleMap);
    }

    public void moveMap(final LatLng latLng) {
        Log.d(TAG, "updateMap " + latLng.latitude + ", " + latLng.longitude);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("현재위치")
                        .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.state)));
                mapManager.carMarkerManagement(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
            }
        });
    }

    public void updateMap(final LatLng latLng) {
        Log.d(TAG, "updateMap " + latLng.latitude + ", " + latLng.longitude);

        if (mapRadar == null || !mapRadar.isAnimationRunning()) {
            mapRadar = new MapRadar(mMap, latLng, this);
            //mapRadar.withClockWiseAnticlockwise(true);
            mapRadar.withDistance(500);
            mapRadar.withClockwiseAnticlockwiseDuration(1);
            mapRadar.withRadarColors(Color.parseColor("#FFA3D2E4"), 0x00fccd29);  //starts from transparent to fuly black
            mapRadar.withOuterCircleTransparency(0.5f);
            mapRadar.withRadarTransparency(0.5f);
        } else {
            mapRadar.withLatLng(latLng);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!mapRadar.isAnimationRunning())
                    mapRadar.startRadarAnimation();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
            }
        });
    }

    public void updateCompleteMap() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mapRadar != null)
                    mapRadar.stopRadarAnimation();
            }
        });
    }

    HashMap<String, Parking> parkingmap = new HashMap<>();

    public void addMarker(final Parking parking) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (parking.getPrice().equals("")) {
                    mMap.addMarker(new MarkerOptions()
                            .position(parking.getLatLng())
                            .title(parking.getName())
                            .snippet(parking.getVicinity())
                            .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.parking)));
                } else {
                    mMap.addMarker(new MarkerOptions()
                            .position(parking.getLatLng())
                            .title(parking.getName())
                            .snippet(parking.getVicinity())
                            .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.paidparking)));
                }

            }
        });
    }

    private void initializeMap(GoogleMap mMap) {
        if (mMap != null) {
            mMap.getUiSettings().setScrollGesturesEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
        }
    }
}
