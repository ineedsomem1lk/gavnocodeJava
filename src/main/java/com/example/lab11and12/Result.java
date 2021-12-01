package com.example.lab11and12;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Result extends AppCompatActivity implements OnClickListener {
    public final static String success = "result";
    public final static String code = "213";
    public final static String BROADCAST_ACTION = "ru.startandroid.develop.p0961servicebackbroadcast";
    BroadcastReceiver br;
    TextView con, url, resp;
    DBHelper dbHelper;
    Button stop;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        con = findViewById(R.id.connection);
        resp = findViewById(R.id.respCode);
        url = findViewById(R.id.url);
        stop = findViewById(R.id.stop);
        stop.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String site;
        if(Connect.urlAddress.length() > 37)
        {
            site = Connect.urlAddress;
            site = site.substring(0, 34);
            site += "...";
        } else site = Connect.urlAddress;
        url.setText("URL: " + site);
        metForCon();
        br = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String result = intent.getStringExtra(success);
                int rs = intent.getIntExtra(code, 0);
                if (result.equals("Error")) {
                    con.setText("Connection: Not");
                    resp.setText("Response Code: Not");
                    url.setText("Such URL does not exist!\nPlease write \"https://\" at the beginning.");
                } else {
                    con.setText("Connection: " + result);
                    resp.setText("Response Code: " + rs);
                    Log.d("#Lab", "--- Insert in connTable: ---");
                    ContentValues cv = new ContentValues();
                    cv.put("url", Connect.urlAddress);
                    cv.put("conn", result);
                    cv.put("resp", rs);
                    long rowID = db.insert("mytable", null, cv);
                    Log.d("#Lab", "row inserted, ID = " + rowID);
                    metForCon();
                }
            }
        };
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intFilt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
        unregisterReceiver(br);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!Connect.first[1]) {
                dbHelper.close();
                unregisterReceiver(br);
                Connect.first[1] = true;
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void metForCon() {
        Intent intent;
        intent = new Intent(this, ServiceConnection.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.stop) {
            if (!Connect.first[1]) {
                dbHelper.close();
                unregisterReceiver(br);
                Connect.first[1] = true;
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "url text,"
                + "resp integer,"
                + "conn text" + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}