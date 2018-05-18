package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUploader {
	FTPClient ftp = null;

	public static void main(String[] args) throws Exception {
		System.out.println("Start");

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					FTPUploader ftpUploader = null;
					try {
						Thread.sleep(10000);
						ftpUploader = new FTPUploader("70.12.114.146", "ozo", "1234");
						ftpUploader.uploadFile("C:\\logs\\sensor.log", "sensor.log", "/home/ozo/");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					count++;
					System.out.println(count + " : ftuUploader Success");
					if (count >= 10) {
						ftpUploader.disconnect();
						break;
					}

				}
			}
		});
		t.start();
		System.out.println("Done");
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