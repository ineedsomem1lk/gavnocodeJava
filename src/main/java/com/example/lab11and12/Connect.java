package com.example.lab11and12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Connect extends AppCompatActivity implements OnClickListener {
    Button urlConnect;
    EditText url;
    public static String urlAddress = "test";
    public static boolean[] first = {true, true};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        urlConnect = (Button) findViewById(R.id.connect);
        urlConnect.setOnClickListener(this);
        urlAddress = "";
        url = findViewById(R.id.urlAddress);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.connect) {
            urlAddress = url.getText().toString();
            first[0] = false;
            first[1] = false;
            Intent intent = new Intent(this, Result.class);
            startActivity(intent);
        }
    }
}