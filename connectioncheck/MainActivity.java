package com.example.connectioncheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broReceiver;
    public String broAction = "mushi.devolp.action";
    public String myLog = "LOG#1";
    public String urlAddress = "";
    private DBManager dbManager;
    ListView listView;
    Boolean checker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.ltView);

        //Створюємо броадкаст + викликаємо сервіс
        broReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.d(myLog,"on Receive");
                sService();
            }
        };
        dbManager = new DBManager(this);
        //Регаєм ресівер
        registerReceiver(broReceiver,new IntentFilter(broAction));

        //https://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(myLog,"YOU CLICK " + position + " " + id);
                Intent intent = new Intent(MainActivity.this, DiagramActivity.class);
                startActivity(intent);
            }
        });
    }

    //Запускаєм сервіс
    public  void sService(){
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("URL", urlAddress);
        startService(intent);
    }

    //Стопаєм сервіс та броадкаст ресівер
    public void stopOnClick(View view) {
        if(!checker) {
            Log.d(myLog,"UNREGISTERED");
            unregisterReceiver(broReceiver);
            checker = true;
        }
        else {
            Log.d(myLog,"REGISTERED");
            registerReceiver(broReceiver, new IntentFilter(broAction));
            checker= false;
        }
        Log.d(myLog,"SERVICE STOPPED");
        stopService(new Intent(this,MyService.class));
    }

    public  void onResume()
    {
        super.onResume();
        dbManager.openDB();
    }

    public void connOnClick(View view) {
        EditText editText = findViewById(R.id.editText1);
        if(editText.getText().toString().contains("https://"))
        {
            urlAddress = editText.getText().toString();
        }
        else urlAddress = "https://" + editText.getText().toString();
        sService();
    }

    public void onDestroy(){
        super.onDestroy();
        dbManager.closeDB();
    }

    public void upgradeOnClick(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                dbManager.getFromDB().split("~"));
        listView.setAdapter(adapter);
    }
}