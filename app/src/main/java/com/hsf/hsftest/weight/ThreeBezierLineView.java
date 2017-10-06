package com.hsf.hsftest.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Thinkpadx240 on 2017/9/1.
 */

public class ThreeBezierLineView extends View{
    private Paint mPaint;//画出直线
    private Paint mBezierPaint;//画出曲线
    private Paint textPaint;//画出文字

    private Path mPath;//路径
    private Point startPoint;
    private Point endPoint;
    // 辅助点1
    private Point assistPoint1;
    // 辅助点2
    private Point assistPoint2;

    private boolean mIsSecondPoint = false;
    public ThreeBezierLineView(Context context) {
        super(context);
        init();
    }

    public ThreeBezierLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThreeBezierLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        // 防抖动
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);


        mBezierPaint = new Paint();
        mBezierPaint.setStrokeWidth(5);
        mBezierPaint.setAntiAlias(true);
        mBezierPaint.setColor(Color.RED);
        // 防抖动
        mBezierPaint.setDither(true);
        mBezierPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setStrokeWidth(3);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        // 防抖动
        textPaint.setDither(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(40);

        mPath = new Path();
        startPoint = new Point(300, 600);
        endPoint = new Point(900, 600);
        assistPoint1 = new Point(600, 900);
        assistPoint2 = new Point(300,900);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(startPoint.x,startPoint.y,8,mPaint);
        canvas.drawText("起点",startPoint.x,startPoint.y,textPaint);
        canvas.drawText("终点",endPoint.x,endPoint.y,textPaint);
        canvas.drawText("控制点1",assistPoint1.x,assistPoint1.y,textPaint);
        canvas.drawText("控制点2",assistPoint2.x,assistPoint2.y,textPaint);
        canvas.drawCircle(endPoint.x,endPoint.y,8,mPaint);
        canvas.drawCircle(assistPoint1.x,assistPoint1.y,8,mPaint);
        canvas.drawCircle(assistPoint2.x,assistPoint2.y,8,mPaint);


        canvas.drawLine(startPoint.x,startPoint.y,assistPoint1.x,assistPoint1.y,mPaint);
        canvas.drawLine(endPoint.x,endPoint.y,assistPoint2.x,assistPoint2.y,mPaint);
        canvas.drawLine(assistPoint1.x,assistPoint1.y,assistPoint2.x,assistPoint2.y,mPaint);

        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.cubicTo(assistPoint1.x, assistPoint1.y, assistPoint2.x, assistPoint2.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, mBezierPaint);//画出路径
        // 画辅助点1
        canvas.drawPoint(assistPoint1.x, assistPoint1.y, mPaint);
        // 画辅助点2
        canvas.drawPoint(assistPoint2.x, assistPoint2.y,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){//多点触控
            case MotionEvent.ACTION_POINTER_DOWN:
                mIsSecondPoint = true;
                break;
            case MotionEvent.ACTION_MOVE:
                assistPoint1.x = (int) event.getX(0);//获取控制点1的横纵坐标
                assistPoint1.y = (int) event.getY(0);
                if (mIsSecondPoint) {
                    assistPoint2.x = (int) event.getX(1);//获取控制点2的横纵坐标
                    assistPoint2.y = (int) event.getY(1);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mIsSecondPoint = false;
                break;
        }
        return true;

    }
}
