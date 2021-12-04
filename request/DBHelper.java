package com.example.request;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    String LOG_TAG = "Kostia";

    public DBHelper(Context context) {
        // конструктор суперкласа
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // Створивю нову таблицю з полями
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "site text,"
                + "status text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

