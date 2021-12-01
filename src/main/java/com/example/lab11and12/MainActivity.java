package com.example.lab11and12;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button connect, status;
    DBHelper dbHelper;
    SimpleAdapter adapter;
    public static boolean[] infset = new boolean[2];
    ListView listView;
    final static int[] counter = new int[2];
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(this);
        status = findViewById(R.id.status);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        if (c.moveToLast()) {
            int respColIndex = c.getColumnIndex("resp");
            int urlColIndex = c.getColumnIndex("url");
            int connColIndex = c.getColumnIndex("conn");
            String site;

            do {
                HashMap<String, String> map;
                map = new HashMap<>();
                if (c.getString(urlColIndex).length() > 45) {
                    site = c.getString(urlColIndex);
                    site = site.substring(0, 43);
                    site += "...";
                } else site = c.getString(urlColIndex);
                map.put("Main", site);
                map.put("Sub", "Response: " + c.getString(respColIndex) + " " + "Connection: " + c.getString(connColIndex));
                arrayList.add(map);
            } while (c.moveToPrevious());
        }
        c.close();

         adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Main", "Sub"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                counter[0] = counter[1] =0 ;
                String item = adapter.getItem(position).toString();
                String a, aConn;
                counter[0] = counter[1] = 0;
                item = item.substring(item.indexOf('/') + 2);
                if (item.indexOf('/') != -1) item = item.substring(0, item.indexOf('/'));
                else item = item.substring(0, item.length() - 1);
                for (int i = 0; i < adapter.getCount(); ++i) {
                    a = adapter.getItem(i).toString();
                    aConn = a;
                    aConn = aConn.substring(0, aConn.indexOf(','));
                    a = a.substring(a.indexOf('/') + 2);
                    if (a.indexOf('/') != -1) a = a.substring(0, a.indexOf('/'));
                    else a = a.substring(0, a.length() - 1);
                    if (a.equals(item)) {
                        if (aConn.contains("Success")) counter[0]++;
                        else counter[1]++;
                    }
                }
                infset[1] = true;
                status.callOnClick();
            }
        });
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect:
                Intent intent = new Intent(this, Connect.class);
                startActivity(intent);
                break;
            case R.id.status:
                if(!infset[1]) {
                    counter[0] = counter[1] = 0;
                    for (int i = 0; i < adapter.getCount(); ++i) {
                        String aConn = adapter.getItem(i).toString();
                        aConn = aConn.substring(0, aConn.indexOf(','));
                        if (aConn.contains("Success")) {
                            counter[0]++;
                        } else counter[1]++;
                    }
                }
                infset[1] = false;
                infset[0]= false;
                Intent statsIntent = new Intent(this, Statistic.class);
                startActivity(statsIntent);
                break;
        }
    }
}