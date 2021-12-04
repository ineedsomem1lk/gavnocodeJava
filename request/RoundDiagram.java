package com.example.request;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RoundDiagram extends AppCompatActivity {

    String LOG_TAG = "Kostia";
    DBHelper dbHelper;
    //Костя будь ласка зміни значення fall на 0. Воно тут для презентації
    int success = 0, fail = 2;
    String loop ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        loop = intent.getStringExtra("site");
        Log.d(LOG_TAG, "Round diagram " + loop);
        //Читаємо статистику
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int statusColIndex = c.getColumnIndex("status");
            int siteColIndex = c.getColumnIndex("site");
            do {
              if(c.getString(siteColIndex).equals(loop)) {
                    if (c.getString(statusColIndex).equals("SUCCESS")) success++;
                    else fail++;
                }
            } while (c.moveToNext());
        } else
            Log.d( LOG_TAG, "0 rows");
        c.close();
        //Тепер малюємо
        //Ініціалізація
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        setContentView(new DrawView(this));
        /*Збільшення висоти, так щоб вийшов квадрат
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(surfaceView.getWidth(),surfaceView.getWidth());
        surfaceView.setLayoutParams(lp);*/
    }

    class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;

        public DrawView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                int radius = 200;
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                        //Наш малюнок
                        Paint paint = new Paint();
                        float sect = success*1.0f/(success+fail);
                        Log.d(LOG_TAG, "Success "+success + " Fail " + fail + " Percent: " + sect);
                        //Візьмемо центр екрана
                        Display display = getWindowManager().getDefaultDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        size.x = size.x/2;
                        size.y = size.y/2;
                        canvas.drawColor(Color.WHITE);
                        paint.setColor(Color.BLACK);
                        canvas.drawCircle(size.x, size.y, radius+2 , paint);
                        paint.setColor(Color.RED);
                        canvas.drawCircle(size.x, size.y, radius , paint);
                        paint.setColor(Color.GREEN);
                        final RectF oval = new RectF();
                        oval.set(size.x-radius, size.y-radius,size.x+radius,size.y+radius);
                        canvas.drawArc(oval, 0, 360*sect,true, paint);
                        //Саме коло намальовано, тепер вдосконалимо його
                        paint.setColor(Color.BLACK);
                        canvas.drawCircle(size.x, size.y, radius-50 , paint);
                        paint.setColor(Color.WHITE);
                        canvas.drawCircle(size.x, size.y, radius-52 , paint);
                        paint.setColor(Color.BLACK);
                        paint.setTextSize(30);
                        canvas.drawText(sect*100+"%", size.x-paint.measureText(sect*100+"%")/2, size.y, paint);
                        canvas.drawText(loop, size.x-paint.measureText(loop)/2, size.y-radius-20, paint);
                        Log.d(LOG_TAG, "End of drawing");
                        running = false;
                    } finally {
                        if (canvas != null) {
                            Log.d(LOG_TAG, "unlock canvas");
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }

    }
}
