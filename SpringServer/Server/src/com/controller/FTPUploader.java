package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUploader {
	FTPClient ftp = null;
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
	static Date currentTime;
	static String dTime;
	static String filename;
	static Calendar cal;
	static String year, month, day;

	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					currentTime = new Date();
					dTime = formatter.format(currentTime);
					System.out.println("============================ [" + dTime
							+ "]       FTP Connection Try      ===============");
					FTPUploader ftpUploader = null;
					try {
						ftpUploader = new FTPUploader("70.12.114.146", "root", "1234");
						ftpUploader.uploadFile("C:\\logs\\sensor.log", "sensor.csv", "/root/sensorLog/");
						ftpUploader.disconnect();
						System.out.println("============================ [" + dTime + "] sensor.csv File Transfer Success ===============");
						Thread.sleep(1000 * 60 * 1); // 1분
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});

		class DayScheduler {
			public DayScheduler() {
				Timer timer = new Timer(false);
				Calendar calendar = Calendar.getInstance();
				// 특정시간부터(미래) 시작하고자 할 경우 아래와같이 .set으로 설정, 안해도 무방함
				calendar.set(2018, 4, 25, 11, 25, 0); // 년,월(0~11,즉 5월은 4),일,시,분,초 지정
				timer.schedule(new MyTask(), new Date(calendar.getTimeInMillis()), 3600000);//테스트용도로 1시간간격 24 * 60 * 60 * 1000, 실제론 하루에 1번만 되면 됨 
			}

			class MyTask extends TimerTask {
				public void run() {
					currentTime = new Date();
					dTime = formatter.format(currentTime);
					cal = new GregorianCalendar();
					cal.add(Calendar.DATE, -1); // 전날 날짜
					year = cal.get(Calendar.YEAR) + "";
					month = (cal.get(Calendar.MONTH) + 1) + "";
					day = cal.get(Calendar.DAY_OF_MONTH) + "";

					if (month.length() == 1) {
						month = "0" + month;
					}
					if (day.length() == 1) {
						day = "0" + day;
					}

					filename = "sensor.log." + year + "-" + month + "-" + day;

					System.out.println("[ Daily ]============================ [" + dTime
							+ "]       FTP Connection Try      ===============");

					File f = new File("C:\\logs\\" + filename);
					if (f.isFile()) {
						System.out.println("[ " + filename + " ] File Found");
					} else {
						System.out.println("[ " + filename + " ] Not Found File");
						return;
					}

					FTPUploader ftpUploader = null;
					try {
						ftpUploader = new FTPUploader("70.12.114.146", "root", "1234");
						ftpUploader.uploadFile("C:\\logs\\" + filename, filename + ".csv", "/root/sensorLog/");
						ftpUploader.disconnect();
						System.out.println("[ Daily ]============================ [" + dTime + "] " + filename
								+ " File Transfer Success ===============");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		new DayScheduler();
		Thread.sleep(1000);
		t.start();

	}

	public FTPUploader(String host, String user, String pwd) throws Exception {
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, pwd);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterRemoteActiveMode(InetAddress.getLocalHost(), 21);
	}

	public void uploadFile(String localFileFullName, String fileName, String hostDir) throws Exception {
		try (InputStream input = new FileInputStream(new File(localFileFullName))) {
			this.ftp.storeFile(hostDir + fileName, input);
		}
	}

	public void disconnect() {
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				f.printStackTrace();
			}
		}
	}

}