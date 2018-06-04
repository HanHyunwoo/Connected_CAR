package com.example.student.asradaivi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class Server {
    final String TAG = "Server   ::";
    final int accelPedal = 0;
    final int breakPedal = 1;
    final int distance = 2;
    final int sleep = 3;

    int msg1;
    int msg2;
    String comm = "";
    Socket socket = null;

    int carState[] = new int[10];

    ServerSocket serverSocket;
    StartServer start;
    Sender sender;
    Boolean flag = true;
    ArrayList<DataOutputStream> list = new ArrayList<>();

    public Server() throws IOException {
        serverSocket = new ServerSocket(9999);
        Log.d("SERVER]", "Success to get Socket, Port : 9999");
        start = new StartServer();
        Log.d("test","Server Ready...");
    }

    public int getMsg1(){
        return msg1;
    }
    public int getMsg2(){
        return msg2;
    }
    public String getComm() { return comm; }

    class StartServer extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
                try {
                    Log.d("SERVER]", "Ready to Accept");
                    socket = serverSocket.accept();
                    Log.d("SERVER]", "accept Complete");

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            return null;
        }

    }

    public void sendMsg(int id, int value){
        Log.d(TAG, "id : "+id+ ", value : "+ value);
        Sender sender = null;
        try {
            if(socket != null)
            sender = new Sender(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(socket != null) {
            sender.setSendMsg(id, value);
            sender.start();
        }
    }

    class Sender extends Thread {
        OutputStream out;
        DataOutputStream dout;
        String sendMsg;

        public Sender() {
        }


        public Sender(Socket socket) throws IOException {
            out = socket.getOutputStream();
            dout = new DataOutputStream(out);
            Log.d("Receiver Class]", "initializing.... complete");
        }

        public void setSendMsg(int id, int value){
            this.sendMsg = id + "," + value;
        }

        @Override
        public void run() {
            if(dout != null){
                try {
                    Log.d("Sender]", "run: !!!!!!!!");
                    dout.writeUTF(sendMsg);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
