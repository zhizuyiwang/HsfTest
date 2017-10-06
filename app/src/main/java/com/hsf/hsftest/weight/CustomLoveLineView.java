package com.hsf.hsftest.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 *@作者 hsf
 *
 *@创建日期 2017/8/29 19:03
 * http://blog.csdn.net/decting/article/details/8580634(桃心线算法)
 * 桃心线
 */


public class CustomLoveLineView extends View{
    private Paint mPaint;
    private int offsetX;
    private int offsetY;
    
    public CustomLoveLineView(Context context) {
        super(context);
        init();
    }

    public CustomLoveLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLoveLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#912345"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float angle = 10;
        while (angle < 30) {
            Point p = getHeartPoint(angle);
            canvas.drawPoint(p.x, p.y, mPaint);
            angle = angle + 0.02f;
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offsetX = w / 2;
        offsetY = h / 2 - 55;
    }

    public Point getHeartPoint(float angle) {
        float t = (float) (angle / Math.PI);
        float x = (float) (19.5 * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float) (-20 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
        return new Point(offsetX + (int) x, offsetY + (int) y);
    }
}
