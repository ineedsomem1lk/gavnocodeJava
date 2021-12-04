package com.example.request;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String BROADCAST = "kostia.request.service", LOG_TAG = "Kostia", FILE_NAME = "sites.txt";
    BroadcastReceiver br;
    String sites = "", results = "";
    ListView listView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        dbHelper = new DBHelper(this);

        sites = load();
        // створюємо BroadcastReceiver
        br = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.d(LOG_TAG, "broadcast received");
                Intent intentS = new Intent(context, MyService.class);
                startService(intentS);
            }
        };
        IntentFilter intFlt = new IntentFilter(BROADCAST);
        registerReceiver(br, intFlt);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    public void startService(View v){
        sendBroadcast(new Intent(BROADCAST));
        v.setClickable(false);
    }

    public void list(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Список сайтів");
        builder.setMessage(sites.replace(" ", "\n"));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void add(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Новий сайт");
        builder.setMessage(sites.replace(" ", "\n"));
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sites+=" "+input.getText();
                save(sites);
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void remove(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Видалити сайт");
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setMessage(sites.replace(" ", "\n"));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sites = sites.replace(input.getText().toString(), "");
                sites = sites.replace("  "," ");
                sites = sites.trim();
                save(sites);
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //--МЕТОДИ ДЛЯ РОБОТИ З ФАЙЛАМИ--//
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

    public void save(String data){ //Збереження даних в файл
        FileOutputStream fos = null;
        Log.d(LOG_TAG, "save " + data);
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, e.getMessage());
        } finally { //Потік необхідно закрити в любому випадку
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(LOG_TAG, e.getMessage());
            }
        }
    }

    public void update(View v){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        String res = "";
        if (c.moveToFirst()) {
            int siteColIndex = c.getColumnIndex("site");
            int statusColIndex = c.getColumnIndex("status");
            do {
                res += c.getString(siteColIndex) + " " + c.getString(statusColIndex) + "@";
            } while (c.moveToNext());
        } else
            Log.d( LOG_TAG, "0 rows");
        c.close();
        res.trim();
        Log.d(LOG_TAG, res);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, res.split("@"));

        listView.setAdapter(adapter);
    }

    public void clean(View v){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("mytable", null, null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new String[0]);
        listView.setAdapter(adapter);
    }

    public void diagram(View v){
        final int[] checkedItem = {0};
        ArrayList<String>available = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int siteColIndex = c.getColumnIndex("site");
            do {
               if(!available.contains(c.getString(siteColIndex))){
                   available.add(c.getString(siteColIndex));
               }
            } while (c.moveToNext());
        } else
            Log.d( LOG_TAG, "0 rows");
        c.close();
        Intent intent = new Intent(MainActivity.this, RoundDiagram.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Кругова діаграма");
        String[] s = new String[available.size()];
        for(int i = 0; i < s.length; i++)s[i]=available.get(i);
        builder.setSingleChoiceItems(s, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem[0] = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra("site", s[checkedItem[0]]);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}