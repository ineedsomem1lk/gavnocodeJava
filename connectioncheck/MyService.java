package com.example.connectioncheck;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "LOG#2";
    public String connectionStatus="";
    public int responseCode=0;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    //https://startandroid.ru/ru/uroki/vse-uroki-spiskom/161-urok-96-service-obratnaja-svjaz-s-pomoschju-broadcastreceiver.html
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(LOG_TAG, "MyService onStartCommand");
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase =  dbHelper.getWritableDatabase();
        //Створюємо потік та методи для провірки чи воркає сайт
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (intent.hasExtra("URL")) {
                    try {
                        URL url = new URL(intent.getStringExtra("URL")); // тут сайт обов`язково з https
                        if (isSiteUp(url)) {
                            connectionStatus = "GOOD";
                        }
                        else {
                            connectionStatus = "BAD";
                        }
                        Log.d(LOG_TAG, connectionStatus);
                        System.out.println(connectionStatus);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        responseCode = connection.getResponseCode(); //Код респонса
                    } catch (IOException e) {
                        connectionStatus = "ERROR";
                        responseCode = 404;
                        e.printStackTrace();
                    }

                    //Заповняю базу даних
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("URL",intent.getStringExtra("URL"));
                    contentValues.put("CONN",connectionStatus);
                    contentValues.put("RESP",responseCode);

                    Log.d(LOG_TAG,intent.getStringExtra("URL")  + " " + responseCode);
                    sqLiteDatabase.insert("mytable",null,contentValues);
                }
                //Сліпаємо поток
                try {
                    Log.d(LOG_TAG,"i need some sleep...");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendBroadcast(new Intent("mushi.devolp.action"));
            }

            public boolean isSiteUp(URL site) { // метод для провірки чи сайт воркає
                try {
                    HttpURLConnection conn = (HttpURLConnection) site.openConnection();
                    return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
                } catch (IOException tout) {
                    return false;
                }
            }
        });
        //Запускаємо поток
        myThread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}