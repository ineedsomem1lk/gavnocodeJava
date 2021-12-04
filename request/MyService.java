package com.example.request;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MyService extends Service {

    String BROADCAST = "kostia.request.service", LOG_TAG = "Kostia", FILE_NAME = "sites.txt";
    DBHelper dbHelper;

    public MyService() {
        dbHelper = new DBHelper(this);
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //https://startandroid.ru/ru/uroki/vse-uroki-spiskom/74-urok-34-hranenie-dannyh-sqlite.html
        ContentValues cv = new ContentValues();

        //Підключення до БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "service started");
        String[] loop = load().split(" ");
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    for(String s : loop){
                        //Код взято з книги Майера Android 4 - програмування програм для
                        // планшетних комп'ютерів і смартфонів
                        try{
                            URL url = new URL(s);

                            URLConnection urlConnection = url.openConnection();
                            HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();

                            int responseCode = httpUrlConnection.getResponseCode();
                            String res= "FAIL";
                            if(responseCode == httpUrlConnection.HTTP_OK){
                                Log.d(LOG_TAG, s + " success");
                                res = "SUCCESS";
                            }else{
                                Log.d(LOG_TAG, s + " fail");
                            }
                            cv.put("site",s);
                            cv.put("status",res);
                            Log.d(LOG_TAG, "insert " + s + " " + res);
                            db.insert("mytable", null, cv);

                        }catch(Exception e){
                            Log.d(LOG_TAG, e.getMessage()+"");
                            e.printStackTrace();

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(5*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(LOG_TAG, "send broadcast from service");
                sendBroadcast(new Intent(BROADCAST));
            }
        });
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    public String load(){ //Читання даних з файлу
        String text = "";
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            text = new String (bytes);
            Log.d(LOG_TAG,"load of data");
        }
        catch(IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, e.getMessage());
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException e){
                e.printStackTrace();
                Log.d(LOG_TAG, e.getMessage());
            }
        }
        Log.d(LOG_TAG, "load " + text);
        return text;
    }
}