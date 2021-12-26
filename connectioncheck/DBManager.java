package com.example.connectioncheck;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DBManager {
    final Context context;
    final DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;


    //https://startandroid.ru/ru/uroki/vse-uroki-spiskom/74-urok-34-hranenie-dannyh-sqlite.html
    public DBManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    //Відкриваємо дб
    public void openDB(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    //Зчитуєм з дб,і пушимо в ліствю
    public String getFromDB()
    {
        Cursor cursor = sqLiteDatabase.query("mytable", null, null, null,
                null, null, null);
        String dbFill = "";
        //Поки курсор може рухатись ми зчитуємо дані,і закидуєм в ліствю
        if (cursor.moveToFirst()) {
            int urlColIndex = cursor.getColumnIndex("URL");
            int connColIndex = cursor.getColumnIndex("CONN");
            int respColIndex = cursor.getColumnIndex("RESP");
            do {
                dbFill += cursor.getString(urlColIndex) +  " | " + cursor.getString(connColIndex) +
                        " | " +cursor.getString(respColIndex) + "~";
            }
            while (cursor.moveToNext());
        }
         else
            Log.d( "LOG#4","db is empty");
        cursor.close();
        Log.d("LOG#4", dbFill);
        return dbFill;
    }

    public void closeDB(){
        dbHelper.close();
    }
}
