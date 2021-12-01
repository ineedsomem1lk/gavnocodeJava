package com.example.lab11and12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class Statistic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    static class DrawView extends View {
        Paint p;
        RectF rectf;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rectf = new RectF(700, 100, 800, 150);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(33,33,33);
            p.setStrokeWidth(10);
            if (!MainActivity.infset[0]) {
                rectf.offsetTo(490, 590);
                rectf.inset(-480, -480);
                MainActivity.infset[0] = true;
            }

            p.setColor(Color.rgb(203 , 37, 45)); // red
            canvas.drawArc(rectf, 90, 360, true, p);

            double successes = MainActivity.counter[0], all = MainActivity.counter[0] + MainActivity.counter[1];
            p.setTextAlign(Paint.Align.CENTER);

            double res = successes / all * 360;

            p.setColor(Color.rgb(37,203,73)); // green
            canvas.drawArc(rectf, 270, (int) res, true, p);

            p.setTextSize(70);

            res = successes / all * 100;

            p.setColor(Color.rgb(180,180,180));
            canvas.drawText("Successes connections: ", 400, 1300, p);
            p.setColor(Color.rgb(37,203,73)); //green
            canvas.drawText((int) res + "%", 850, 1300, p);

            p.setColor(Color.rgb(180,180,180));
            canvas.drawText("Bad connections: ", 290, 1400, p);
            p.setColor(Color.rgb(203 , 37, 45)); // red
            canvas.drawText(100 - (int)res + "%", 640, 1400, p);

            p.setColor(Color.rgb(180,180,180));
            canvas.drawText("Total connections to site: ", 410, 1500, p);
            canvas.drawText(""+(int)all, 850, 1500, p);

        }
    }
}