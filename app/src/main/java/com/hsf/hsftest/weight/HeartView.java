package com.hsf.hsftest.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hsf.hsftest.model.Bloom;
import com.hsf.hsftest.model.Garden;
import com.hsf.hsftest.model.Point;

import java.util.ArrayList;

/**
 * @作者 hsf
 * @创建日期 2017/9/2 14:12
 * http://blog.csdn.net/huachao1001/article/details/51489582
 */


public class HeartView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder surfaceHolder;
    int offsetX;
    int offsetY;
    private Garden garden;
    private int width;
    private int height;
    private Paint backgroundPaint;
    private boolean isDrawing = false;
    private Bitmap bm;
    private Canvas canvas;
    private int heartRadio = 1;
    ArrayList<Bloom> blooms = new ArrayList<>();
    private boolean stop = false;

    public HeartView(Context context) {
        super(context);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        garden = new Garden();
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.rgb(0xff, 0xff, 0xe0));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.width = width;
        this.height = height;
        //我的手机宽度像素是1080，发现参数设置为30比较合适，这里根据不同的宽度动态调整参数
        heartRadio = width * 25 / 1080;

        offsetX = width / 2;
        offsetY = height / 2 - 55;
        bm = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        canvas = new Canvas(bm);
        drawOnNewThread();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    //开启一个新线程绘制
    private void drawOnNewThread() {
        new Thread() {
            @Override
            public void run() {
                if (isDrawing)
                    return;
                isDrawing = true;
                float angle = 10;
                while (true) {
                    Bloom bloom = getBloom(angle);
                    if (bloom != null) {
                        blooms.add(bloom);
                    }
                    if (angle >= 30) {
                        break;
                    } else {
                        angle += 0.2;
                    }
                    if (stop) {
                        if (blooms != null) {
                            blooms.clear();
                        }
                        drawHeart();
                        isDrawing = false;
                        return;
                    }
                    drawHeart();
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isDrawing = false;
            }
        }.start();
    }

    //绘制列表里所有的花朵
    private void drawHeart() {
        canvas.drawRect(0, 0, width, height, backgroundPaint);
        for (Bloom b : blooms) {
            b.draw(canvas);
        }
        Canvas c = surfaceHolder.lockCanvas();
        c.drawBitmap(bm, 0, 0, null);

        surfaceHolder.unlockCanvasAndPost(c);
    }

    public void reDraw() {
        stop = true;
        if (blooms != null) {
            blooms.clear();
        }
        SystemClock.sleep(100);
        stop = false;
        drawOnNewThread();
    }

    private Bloom getBloom(float angle) {

        Point p = getHeartPoint(angle);
        boolean draw = true;
        /**循环比较新的坐标位置是否可以创建花朵,
         * 为了防止花朵太密集
         * */
        for (int i = 0; i < blooms.size(); i++) {

            Bloom b = blooms.get(i);
            Point bp = b.getPoint();
            float distance = (float) Math.sqrt(Math.pow(p.x - bp.x, 2) + Math.pow(p.y - bp.y, 2));
            if (distance < Garden.Options.maxBloomRadius * 1.5) {
                draw = false;
                break;
            }
        }
        //如果位置间距满足要求，就在该位置创建花朵并将花朵放入列表
        if (draw) {
            Bloom bloom = garden.createRandomBloom(p.x, p.y);
            return bloom;
        }
        return null;
    }

    /**
     * 得到每一朵花的花芯的圆心坐标
     *
     * @param angle
     * @return
     */
    public Point getHeartPoint(float angle) {
        float t = (float) (angle / Math.PI);
        float x = (float) (heartRadio * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float) (-heartRadio * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));

        return new Point(offsetX + (int) x, offsetY + (int) y);
    }
}
