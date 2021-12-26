package com.example.connectioncheck;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DiagramActivity extends AppCompatActivity {

    int  good=0,bad=0;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(new Draw(this));

        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("mytable", null, null, null,
                null, null, null);
        //Підраховуємо статуси конектів
        if (cursor.moveToFirst()) {
            int connColIndex = cursor.getColumnIndex("CONN");
            do {
                if(cursor.getString(connColIndex).equals("GOOD")){
                    good++;
                }
                else  bad++;
            } while (cursor.moveToNext());
        }
        else
            Log.d( "LOG#4","db is empty");
        cursor.close();
    }

    /*http://developer.alexanderklimov.ru/android/catshop/android.graphics.canvas.php
    + https://www.youtube.com/watch?v=0yahz_0v_as&t=180s&ab_channel=StartAndroid
     */
    class Draw extends View
    {
        Paint myPaint,p;
        public Draw(Context context) {
            super(context);
            myPaint = new Paint();
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(60);
        }
        @Override
        public void onDraw(Canvas canvas){
            setBackgroundColor(212529);
            System.out.println("GOOD: " + good + " BAD: " + bad);
            float w = (float) getWidth(),h = (float) getHeight(),radius;
            if (w > h) { radius = h / 4; }
            else { radius = w / 4; }

            float cx = w / 2,cy = h / 2;
            float f = bad * 1f/(good+bad); // переводимо в проценти
            System.out.println(f);

            @SuppressLint("DrawAllocation")
            RectF contentRect = new RectF(cx - radius, cy - radius, cx + radius,
                    cy + radius);

            myPaint.setStrokeWidth(radius * 0.2f);
            myPaint.setStyle(Paint.Style.STROKE);

            //Синій круг
            myPaint.setColor(Color.rgb(0,117,255)); //Brandeis Blue
            canvas.drawArc(contentRect, 180, 360, false, myPaint);
            //Червоний круг
            myPaint.setColor(Color.rgb(254,26,79)); //Crayola red
            canvas.drawArc(contentRect, 270, 360* f, false, myPaint);

            //Робимо текст (https://startandroid.ru/ru/uroki/vse-uroki-spiskom/329-urok-149-risovanie-tekst.html)
            //Жирний текст
            float textWidth = p.measureText(good + "ON");
            float textHeight = p.measureText(bad + " OFF");
            canvas.translate(cx -(textWidth/2)-5, cy -(textHeight/2)+20);
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText(good + " ON", 0, 0, p);

            //Курсивний текст
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
            canvas.translate(10, 160);
            canvas.drawText(bad + " OFF", 0, 0, p);
        }
    }

}