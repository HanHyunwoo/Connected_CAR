package com.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test_WebRequest {

	public static void main(String[] args) throws IOException {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					int carid = randomRange(1000, 1003);
					int accel = randomRange(0, 512);
					int decel = randomRange(0, 512);
					int safetydis = randomRange(0, 1);
					int snoose = randomRange(0, 1);
					int speed = randomRange(0, 130);
					int batterty = randomRange(22000, 25000);

					if (accel > 0) {
						decel = 0;
					}

					URL url;
					try {
						url = new URL("http://70.12.114.143/Server/logAdd.do?CARID=" + carid + "&ACCEL=" + accel
								+ "&DECEL=" + decel + "&SAFETYDIS=" + safetydis + "&SNOOZE=" + snoose + "&SPEED="
								+ speed + "&BATTERY=" + batterty);
						//System.out.println("URL :" + url.toExternalForm());
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.getHeaderFields().entrySet();
						// conn.setRequestMethod("GET"); //기본 get 방식
						conn.setConnectTimeout(1000); // 3초
						conn.disconnect();
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public static int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}

}
