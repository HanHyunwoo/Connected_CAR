package com.example.student.asradaivi;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.arsy.maps_library.MapRadar;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapText;
import com.beardedhen.androidbootstrap.font.MaterialIcons;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import at.markushi.ui.CircleButton;

import static com.beardedhen.androidbootstrap.font.FontAwesome.FA_HEART;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String TAG = String.format("%20s", "MainActivity :: ");
    private final int MENU_HOME = 0;
    private final int MENU_SCORE = 1;
    private final int MENU_ANALYSIS = 2;
    Server server;
    LinearLayout ll_menu, ll_call, ll_map, ll_home;
    TextView tv_time;
    TextView[] menu = new TextView[3];
    Date dt;
    SimpleDateFormat time;
    WebView wv_score, wv_energy;
    GoogleMap mMap;
    MapManager mapManager;
    private MapRadar mapRadar;
    ContactAdapter contactAdapter;
    ToggleButton btn_led, btn_wiper, btn_snooze;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            server = new Server();
            server.start.execute();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        menu[0] = findViewById(R.id.tv_home);
        menu[1] = findViewById(R.id.tv_score);
        menu[2] = findViewById(R.id.tv_analysis);

        wv_score = findViewById(R.id.wv_score);
        wv_energy = findViewById(R.id.wv_energy);
        wv_score.setWebViewClient(new WebViewClient());
        wv_energy.setWebViewClient(new WebViewClient());
        WebSettings webSettings1 = wv_score.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        WebSettings webSettings2 = wv_energy.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        wv_score.loadUrl(ApplicationData.score_url);

        ll_home = findViewById(R.id.ll_home);
        ll_home.setVisibility(View.VISIBLE);
        changeMenuColor(MENU_HOME);

        ll_call = findViewById(R.id.ll_call);
        ll_call.setVisibility(View.VISIBLE);

        // List View setting
        LinearLayout container = findViewById(R.id.container_h);
        contactAdapter = new ContactAdapter(this, container);

        ListView listView = findViewById(R.id.contactlist);
        listView.setAdapter(contactAdapter);

        ll_map = findViewById(R.id.ll_map);
        ll_map.setVisibility(View.INVISIBLE);

        tv_time = findViewById(R.id.tv_time);
        time = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss a");

        btn_led = findViewById(R.id.btn_led);
        btn_led.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    server.sendMsg(5, 2);
                } else {
                    server.sendMsg(5, 1);
                }
            }
        });

        btn_wiper = findViewById(R.id.btn_wiper);
        btn_wiper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    server.sendMsg(6, 2);
                } else {
                    server.sendMsg(6, 1);
                }
            }
        });

        btn_snooze =findViewById(R.id.btn_snooze);
        btn_snooze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    server.sendMsg(7, 2);
                } else {
                    server.sendMsg(7, 1);
                }
            }
        });

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

    public void changeMenuColor(int index) {
        for (int i = 0; i < menu.length; i++) {
            if (i == index) {
                menu[i].setBackgroundResource(R.color.colorAccent);
                menu[i].setTextColor(getResources().getColor(android.R.color.background_light));
            } else {
                menu[i].setBackgroundResource(android.R.color.background_light);
                menu[i].setTextColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }

    public void ClickSensor(View v) {
        if (v.getId() == R.id.btn_c) {
            server.sendMsg(5, 1);
        } else if (v.getId() == R.id.btn_left) {
            server.sendMsg(5, 1);
        } else if (v.getId() == R.id.btn_right) {
            server.sendMsg(5, 1);
        }
    }

    public void ClickBTN(View v) {
        if (v.getId() == R.id.tv_home) {
            changeMenuColor(MENU_HOME);
            mapManager.setMapView(false);
            ll_home.setVisibility(View.VISIBLE);
            ll_call.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.tv_score) {
            contactAdapter.initContacts();
            changeMenuColor(MENU_SCORE);
            mapManager.setMapView(false);
            ll_call.setVisibility(View.VISIBLE);
            ll_home.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.tv_analysis) {
            changeMenuColor(MENU_ANALYSIS);
            ll_map.setVisibility(View.VISIBLE);
            ll_home.setVisibility(View.INVISIBLE);
            ll_call.setVisibility(View.INVISIBLE);
            initializeMap(mMap);
            if (mapManager != null)
                mapManager.setMapView(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady");
        mMap = googleMap;
        mapManager = new MapManager(this, googleMap);
    }

    public void addCurrentLoc(final LatLng latLng) {
        Log.d(TAG, "addCurrentLoc ");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("현재위치")
                        .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.state)));
                mapManager.carMarkerManagement(marker);
            }
        });
    }

    public void moveMap(final LatLng latLng) {
        Log.d(TAG, "updateMap " + latLng.latitude + ", " + latLng.longitude);
        addCurrentLoc(latLng);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
            }
        });
    }

    public void updateMap(final LatLng latLng) {
        Log.d(TAG, "updateMap " + latLng.latitude + ", " + latLng.longitude);

        if (mapRadar == null || !mapRadar.isAnimationRunning()) {
            mapRadar = new MapRadar(mMap, latLng, this);
            //mapRadar.withClockWiseAnticlockwise(true);
            mapRadar.withDistance(300);
            mapRadar.withClockwiseAnticlockwiseDuration(2);
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mapRadar != null)
                    mapRadar.stopRadarAnimation();
            }
        });
    }

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
                    Log.d(TAG, "paid parking lot" + parking.getName() +", " + parking.getPrice());
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
