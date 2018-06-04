package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HadoopUpload {
	
	public static void HadoopUpload() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			conn = DriverManager.getConnection("jdbc:hive2://70.12.114.146:10000/default", "root", "1234");
			stmt = conn.createStatement();
			stmt.execute(
					"LOAD DATA LOCAL INPATH '/root/sensorLog/sensor.csv' INTO TABLE HEXAINFO PARTITION (occurrenceYear='2099');");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Success");
	}
	

	public static void main(String[] args) {
		HadoopUpload();
	}
}
