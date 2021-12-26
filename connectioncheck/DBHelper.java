package com.example.connectioncheck;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    //https://startandroid.ru/ru/uroki/vse-uroki-spiskom/74-urok-34-hranenie-dannyh-sqlite.html
    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDataBase", null, 1);
    }

    //Створюємо табличку
    public void onCreate(SQLiteDatabase db)
    {
        Log.d("LOG#3", "--- onCreate database ---");

        db.execSQL("CREATE TABLE IF NOT EXISTS mytable ("
                + "ID integer primary key autoincrement,"
                + "URL text,"
                + "CONN text,"
                + "RESP integer" + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);
    }
}
