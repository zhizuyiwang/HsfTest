package com.hsf.hsftest.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Thinkpadx240 on 2017/9/1.
 */

public class TwoBezierView extends View{
    private Paint mPaint;
    private Paint mBezierPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;
    private float assistPointX;
    private float assistPointY;
    // 辅助点
    private Point assistPoint;
    public TwoBezierView(Context context) {
        super(context);
        init(context);
    }

    public TwoBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TwoBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mBezierPaint = new Paint();
        mBezierPaint.setColor(Color.RED); // 画笔颜色
        // 笔宽
        mBezierPaint.setStrokeWidth(5);
        // 空心
        mBezierPaint.setStyle(Paint.Style.STROKE);
        // 抗锯齿
        mBezierPaint.setAntiAlias(true);
        // 防抖动
        mBezierPaint.setDither(true);

        mPaint = new Paint();
        mPath = new Path();
        startPoint = new Point(300, 600);
        endPoint = new Point(900, 600);
        assistPoint = new Point(600, 900);
        assistPointX = assistPoint.x;
        assistPointY = assistPoint.y;

        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        // 画笔颜色
        mPaint.setColor(Color.BLACK);
        // 笔宽
        mPaint.setStrokeWidth(2);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(50);//Size优先级更高
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(startPoint.x, startPoint.y, 8, mPaint);
        canvas.drawText("起点", startPoint.x, startPoint.y, mPaint);

        canvas.drawCircle(endPoint.x,endPoint.y, 8, mPaint);
        canvas.drawText("终点",endPoint.x, endPoint.y, mPaint);

        canvas.drawCircle(assistPointX, assistPointY, 8, mPaint);
        canvas.drawText("控制点", assistPointX, assistPointY, mPaint);

        canvas.drawLine(startPoint.x, startPoint.y,  assistPointX, assistPointY, mPaint);
        canvas.drawLine(endPoint.x,endPoint.y, assistPointX, assistPointY, mPaint);
        // 重置路径
        mPath.reset();
        // 起点
        mPath.moveTo(startPoint.x,startPoint.y);
        // 重要的就是这句
        mPath.quadTo(assistPointX,assistPointY,endPoint.x,endPoint.y);
        // 画路径
        canvas.drawPath(mPath,mBezierPaint);
        // 画辅助点
        canvas.drawPoint(assistPointX,assistPointY,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                assistPointX = (int) event.getX();
                assistPointY = (int) event.getY();
                Log.i("TAG", "assistPointX = " + assistPointX);
                Log.i("TAG", "assistPointY = " + assistPointY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                ValueAnimator animX = ValueAnimator.ofFloat(assistPointX, getWidth() / 2);
                animX.setDuration(500);
                animX.setInterpolator(new OvershootInterpolator());
                animX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        assistPointX = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                animX.start();
                ValueAnimator animY = ValueAnimator.ofFloat(assistPointY, getHeight() / 2);
                animY.setDuration(500);
                animY.setInterpolator(new OvershootInterpolator());
                animY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        assistPointY = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                animY.start();
                break;
        }
        return true;
    }

}
