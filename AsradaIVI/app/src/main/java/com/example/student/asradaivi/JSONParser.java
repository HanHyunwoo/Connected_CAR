package com.example.student.asradaivi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class JSONParser {
    String TAG = "JSONParser";
    String charset = "UTF-8";
    DataOutputStream dataOutputStream;
    StringBuilder stringBuilder;
    URL urlObj;
    JSONObject jsonObject = null;
    String paramsString;

    class postRequestThread extends Thread {
        String url;
        String params;
        String key = "&key=" + ApplicationData.location_key;
        MapManager mapManager;
        boolean flag = true;

        public postRequestThread(MapManager mapManager, String url, String params) {
            this.mapManager = mapManager;

            this.url = url;
            this.params = params;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            while (flag) {
                HttpURLConnection httpURLConnection = null;

                try {
                    Log.d(TAG, "postRequestThread  : " + url + params + key);
                    urlObj = new URL(url + params + key);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Accept-Charset", charset);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.connect();
                    dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONObject jo = getJsonObject(httpURLConnection);

                try {
                    if(jo.getString("status").equals("ZERO_RESULTS")) {
                        Log.d(TAG, "ZERO_RESULT");
                        break;
                    }


                    mapManager.addMarkers(jo.getJSONArray("results"));

                    /* Check next token */
                    params = "pagetoken=" + jo.getString("next_page_token");
                } catch (JSONException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

/*
    class getRequestThread extends Thread {
        String url;
        boolean flag = true;

        public getRequestThread (String url) {
            this.url = url;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            while (flag) {
                HttpURLConnection httpURLConnection = null;

                try {
                    urlObj = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setDoOutput(false);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Accept-Charset", charset);
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONObject jo = getJsonObject(httpURLConnection);
            }
        }
    }
*/

    public JSONObject getJsonObject(HttpURLConnection httpURLConnection) {
        try {
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Log.d("JSON Parser", "Result: " + stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpURLConnection.disconnect();

        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jsonObject;
    }

    public void makeHttpRequest(MapManager mapManager, String url, String params, String method) {
        if (method.equals("POST")) {
            new postRequestThread(mapManager, url, params).start();
        } else if (method.equals("GET")) {
            //new getRequestThread(url).start();
        }
    }
}
