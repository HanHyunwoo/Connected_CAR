package com.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vo.Analyzed;

public class HadoopDataToss {
	Analyzed an;
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://70.12.114.143/Server/analyzedAdd.do");
			System.out.println("URL :" + url.toExternalForm());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.getHeaderFields().entrySet();
			conn.setConnectTimeout(3000); // 3초
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Analyzed> call() throws Exception {
		System.out.println("드러왔따");
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://70.12.114.146:10000/default", "root", "1234");
		Statement stmt = conn.createStatement();
		//CarId=1000, DateStart=2018-06-01 00:47:30, DateEnd=2018-06-01 00:47:31, Burst=0, Deceleration=0, QuickStart=0, SuddenStop=0, SafetyDis=1, Snooze=0, Battery=24858
		String order_hive = "select t1.carid, t1.date, t2.date,\r\n" + 
				"if (t2.speed-t1.speed>=30 and t1.speed!=0,1,0) as Burst, \r\n" + 
				"if (t1.speed-t2.speed>=30 and t1.speed!=0,1,0) as Deceleration, \r\n" + 
				"if (t2.speed-t1.speed>=30 and t1.speed=0,1,0) as QuickStart, \r\n" + 
				"if (t1.speed-t2.speed>=30 and t1.speed=0,1,0) as SuddenStop, \r\n" + 
				"cast(round((t1.SAFETYDIS+t2.SAFETYDIS)/2) as int), cast(round((t1.SNOOZE+t2.SNOOZE)/2) as int), t2.battery\r\n" + 
				"from (SELECT carid, date, ROUND(AVG(speed)) as speed, SAFETYDIS, SNOOZE FROM loginfo where unix_timestamp() - unix_timestamp(date,'yyyy-MM-dd HH:mm:ss') \r\n" + 
				"\r\n" + 
				"<=300 \r\n" + 
				"GROUP BY date,carid,SAFETYDIS, SNOOZE) t1, (SELECT carid, date, ROUND(AVG(speed)) as speed, SAFETYDIS, battery, SNOOZE \r\n" + 
				"FROM loginfo where unix_timestamp() - unix_timestamp(date,'yyyy-MM-dd HH:mm:ss') <=301 GROUP BY date,carid,SAFETYDIS, battery, SNOOZE) t2 \r\n" + 
				"where ROUND(  (unix_timestamp(t2.date,'yyyy-MM-dd HH:mm:ss')-unix_timestamp(t1.date,'yyyy-MM-dd HH:mm:ss'))) =1 and t1.carid = t2.carid";
		
		ResultSet rs = stmt.executeQuery(order_hive);
		
		List<Analyzed> list = new ArrayList<Analyzed>();
	
		while (rs.next()) {
			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5)
					+ rs.getString(6) + rs.getString(7) + rs.getString(8) + rs.getString(9) + rs.getString(10));
			an= new Analyzed();
			an.setCarId(rs.getString(1));
			an.setDateStart(rs.getString(2));
			an.setDateEnd(rs.getString(3));
			an.setBurst(rs.getInt(4));
			an.setDeceleration(rs.getInt(5));
			an.setQuickStart(rs.getInt(6));
			an.setSuddenStop(rs.getInt(7));
			an.setSafetyDis(rs.getInt(8));
			an.setSnooze(rs.getInt(9));
			an.setBattery(rs.getInt(10));
			
			list.add(an);
		}
		
		
		
//		Analyzed an= new Analyzed();
//		an.setCarId(rs.get);
//		an.setDateStart(rs.getString(2));
//		an.setDateEnd(rs.getString(3));
//		an.setBurst(rs.getInt(4));
//		an.setDeceleration(rs.getInt(5));
//		an.setQuickStart(rs.getInt(6));
//		an.setSuddenStop(rs.getInt(7));
//		an.setSafetyDis(rs.getInt(8));
//		an.setSnooze(rs.getInt(9));
////		an.setBattery(rs.getInt(10));
//		System.out.println(an.getBattery());
//		System.out.println(an.getBurst());
//		System.out.println(an.getCarId());
//		System.out.println(an.getDateEnd());
//		System.out.println(an.getDateStart());
//		System.out.println(an.getDeceleration());
//		System.out.println(an.getQuickStart());
//		System.out.println(an.getSafetyDis());
//		System.out.println(an.getSnooze());
//		System.out.println( an.getSuddenStop());
		conn.close();
		System.out.println("Success..!");
		return list;
	}

}
