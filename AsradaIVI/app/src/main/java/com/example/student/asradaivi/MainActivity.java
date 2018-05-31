package com.example.student.asradaivi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static com.beardedhen.androidbootstrap.font.FontAwesome.FA_HEART;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String TAG = String.format("%20s", "MainActivity :: ");
    private final int MENU_HOME = 0;
    private final int MENU_ENERGY = 1;
    private final int MENU_SCORE = 2;
    private final int MENU_ANALYSIS = 3;
    LinearLayout ll_menu, ll_score, ll_map, ll_home;
    TextView tv_time;
    TextView[] menu = new TextView[4];
    Date dt;
    SimpleDateFormat time;
    WebView wv_search, wv_hexa;
    RelativeLayout rl_energy;
    GoogleMap mMap;
    BootstrapButton btn_led, btn_wiper;
    MapManager mapManager;
    private MapRadar mapRadar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        menu[0] = findViewById(R.id.tv_home);
        menu[1] = findViewById(R.id.tv_energy);
        menu[2] = findViewById(R.id.tv_score);
        menu[3] = findViewById(R.id.tv_analysis);

        wv_search = findViewById(R.id.wv_search);
        wv_hexa = findViewById(R.id.wv_hexa);
        wv_search.setWebViewClient(new WebViewClient());
        wv_hexa.setWebViewClient(new WebViewClient());
        WebSettings webSettings1 = wv_search.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        WebSettings webSettings2 = wv_hexa.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        wv_search.loadUrl("https://www.google.com/");

        ll_home = findViewById(R.id.ll_home);
        ll_home.setVisibility(View.VISIBLE);
        changeMenuColor(MENU_HOME);

        rl_energy = findViewById(R.id.rl_energy);
        rl_energy.setVisibility(View.INVISIBLE);

        ll_score = findViewById(R.id.ll_score);
        ll_score.setVisibility(View.INVISIBLE);

        ll_map = findViewById(R.id.ll_map);
        ll_map.setVisibility(View.INVISIBLE);

        tv_time = findViewById(R.id.tv_time);
        time = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss a");

        btn_led = findViewById(R.id.btn_led);
        btn_led.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    bootstrapButton.setText("LED OFF");
                } else {
                    bootstrapButton.setText("LED ON");
                }
            }
        });

        btn_wiper = findViewById(R.id.btn_wiper);
        btn_wiper.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    bootstrapButton.setText("Wiper 작동 중");
                } else {
                    bootstrapButton.setText("Wiper 작동 중지");
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

    public void ClickBTN(View v) {
        if (v.getId() == R.id.tv_home) {
            changeMenuColor(MENU_HOME);
            mapManager.setMapView(false);
            ll_home.setVisibility(View.VISIBLE);
            ll_score.setVisibility(View.INVISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.tv_energy) {
            changeMenuColor(MENU_ENERGY);
            mapManager.setMapView(false);
            ll_score.setVisibility(View.INVISIBLE);
            rl_energy.setVisibility(View.VISIBLE);
            ll_home.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
            wv_hexa.loadUrl("http://70.12.114.144/Server/energy.do");
        } else if (v.getId() == R.id.tv_score) {
            changeMenuColor(MENU_SCORE);
            mapManager.setMapView(false);
            ll_score.setVisibility(View.VISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            ll_home.setVisibility(View.INVISIBLE);
            ll_map.setVisibility(View.INVISIBLE);
            wv_hexa.loadUrl("http://70.12.114.144/dash/charts/hexaChart.jsp");
            //DO_Score();
        } else if (v.getId() == R.id.tv_analysis) {
            changeMenuColor(MENU_ANALYSIS);

            ll_map.setVisibility(View.VISIBLE);
            ll_home.setVisibility(View.INVISIBLE);
            ll_score.setVisibility(View.INVISIBLE);
            rl_energy.setVisibility(View.INVISIBLE);
            initializeMap(mMap);
            if (mapManager != null)
                mapManager.setMapView(true);
        }
    }

    public void ClickSensorBTN(View v) {
        Log.d("TAG", "더eoeoeo댓");
        if (v.getId() == btn_led.getId()) {
            Log.d("TAG", "더ㅔㅅ");
            if (btn_led.isSelected())
                btn_led.setText("off");
            else
                btn_led.setText("on");
        } else if (v.getId() == R.id.btn_wiper) {
            if (btn_wiper.isSelected())
                btn_wiper.setText("off");
            else
                btn_wiper.setText("on");
        }
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
