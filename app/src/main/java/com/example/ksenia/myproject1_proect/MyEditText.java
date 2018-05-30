package com.example.ksenia.myproject1_proect;

/*
  Created by Ksenia on 01.05.2018.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


//класс для текстового поля, где надо пронумеровать строки
public class MyEditText extends android.support.v7.widget.AppCompatEditText {


    private Rect rect;
    private Paint paint;
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#EFAF8C"));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int densityDpi = dm.heightPixels;
        paint.setTextSize(densityDpi/39);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseline = getBaseline();
        for (int i = 0; i < getLineCount(); i++) {
            canvas.drawText("" + (i+1), rect.left, baseline, paint);
            baseline += getLineHeight();
        }
    }
}