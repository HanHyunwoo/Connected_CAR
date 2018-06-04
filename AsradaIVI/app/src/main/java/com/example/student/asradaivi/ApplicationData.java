package com.example.student.asradaivi;

import com.google.android.gms.maps.model.LatLng;

public class ApplicationData {

    static final String location_url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    /*
      https://developers.google.com/places/web-service/search
      https://developers.google.com/places/web-service/supported_types
      https://developers.google.com/maps/faq#languagesupport
    */
    static final String location_key = "AIzaSyB-GLaJGgFAlhF-1nDm0gQt-N9XCH5Lp0Y";
    static final String score_url = "http://70.12.114.144/Server/hexaChart2.jsp";
    static final String energy_url = "http://70.12.114.144/Server/hexaChart2.jsp";
    static final double updateDistance = 0.004;
    static final LatLng currentLatLng = new LatLng(37.490, 127.025);
    static final int locationRadius = 500;

    static final int SNOOZE = 4;
    static final int LED = 5;
    static final int MOTOR = 6;
}
