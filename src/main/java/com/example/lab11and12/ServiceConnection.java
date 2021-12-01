package com.example.lab11and12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServiceConnection extends Service {
    ExecutorService es;

    public void onCreate() {
        super.onCreate();
        es = Executors.newFixedThreadPool(1);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        MyRun mr = new MyRun();
        es.execute(mr);
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {
        public String suc = "";
        public Integer code = 0;

        public void run() {
            Intent intent = new Intent(Result.BROADCAST_ACTION);
            if (Connect.first[0]) {
                try {
                    TimeUnit.MINUTES.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Connect.first[0] = true;
            connect();
            intent.putExtra(Result.success, suc);
            intent.putExtra(Result.code, code);
            sendBroadcast(intent);
        }

        public void connect() {
            try {
                URL url = new URL(Connect.urlAddress);
                if (isSiteUp(url)) suc = "Success";
                else suc = "Bad";
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                code = connection.getResponseCode();
            } catch (IOException e) {
                suc = "Error";
                e.printStackTrace();
            }
        }

        public boolean isSiteUp(URL site) {
            try {
                HttpURLConnection conn = (HttpURLConnection) site.openConnection();
                conn.getContent();
                return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
            } catch (IOException tout) {
                return false;
            }
        }
    }
}