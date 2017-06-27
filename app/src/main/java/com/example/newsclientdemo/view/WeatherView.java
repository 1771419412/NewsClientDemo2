package com.example.newsclientdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 雪无痕 on 2017/6/1.
 */

public class WeatherView extends View{
    public WeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
    }
}
