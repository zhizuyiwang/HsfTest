package com.hsf.hsftest.image.imagezoom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hsf.hsftest.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowImageActivity extends AppCompatActivity implements View.OnTouchListener{


    private ImageView imgv;

    private PointF point0 = new PointF();
    private PointF pointM = new PointF();

    private final float ZOOM_MIN_SPACE = 10f;
    private String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479174352&di=b4ec34b54effa54f3c5be76e94381b44&src=http://a.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11daf25f19bc12c8fcc3ce2d46.jpg";

    // 设定事件模式
    private final int NONE = 0;
    private final int DRAG = 1;
    private final int ZOOM = 2;
    private int mode = NONE;

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    // 获取屏幕分辨率。以480*320为例
    private int displayHeight = 480;
    private int displayWidth = 320;

    private float minScale = 1f;
    private float maxScale = 10f;
    private float currentScale = 1f;
    private float oldDist;

    private Bitmap bm;
    private int imgWidth;
    private int imgHeight;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_show_image);
        init();
    }

    private void init() {
        imgv = (ImageView) findViewById(R.id.imgv);
        imgv.setOnTouchListener(this);
        new Thread(){
            @Override
            public void run() {
                super.run();
                bm = returnBitMap(url);
                imgv.post(new Runnable() {
                    @Override
                    public void run() {
                        imgWidth = bm.getWidth();
                        imgHeight = bm.getHeight();
                        imgv.setImageBitmap(bm);
                        minScale = getMinScale();
                        matrix.setScale(minScale, minScale);
                        center();
                        imgv.setImageMatrix(matrix);
                    }
                });
            }
        }.start();
    }
    private Bitmap returnBitMap(String url) {
        if(null==url || "".equals(url)){
            return null;
        }
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(2000);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView imgv = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                point0.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > ZOOM_MIN_SPACE) {
                    savedMatrix.set(matrix);
                    setMidPoint(event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                whenMove(event);
                break;

        }
        imgv.setImageMatrix(matrix);
        checkView();
        return true;
    }

    private void whenMove(MotionEvent event) {
        switch (mode) {
            case DRAG:
                matrix.set(savedMatrix);
                matrix.postTranslate(event.getX() - point0.x, event.getY()
                        - point0.y);
                break;
            case ZOOM:
                float newDist = spacing(event);
                if (newDist > ZOOM_MIN_SPACE) {
                    matrix.set(savedMatrix);
                    float sxy = newDist / oldDist;
                    System.out.println(sxy + "<==放大缩小倍数");
                    matrix.postScale(sxy, sxy, pointM.x, pointM.y);
                }
                break;
        }
    }

    // 两个触点的距离
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void setMidPoint(MotionEvent event) {
        float x = event.getX(0) + event.getY(1);
        float y = event.getY(0) + event.getY(1);
        pointM.set(x / 2, y / 2);
    }

    // 图片居中
    private void center() {
        RectF rect = new RectF(0, 0, imgWidth, imgHeight);
        matrix.mapRect(rect);
        float width = rect.width();
        float height = rect.height();
        float dx = 0;
        float dy = 0;

        if (width < displayWidth)
            dx = displayWidth / 2 - width / 2 - rect.left;
        else if (rect.left > 0)
            dx = -rect.left;
        else if (rect.right < displayWidth)
            dx = displayWidth - rect.right;

        if (height < displayHeight)
            dy = displayHeight / 2 - height / 2 - rect.top;
        else if (rect.top > 0)
            dy = -rect.top;
        else if (rect.bottom < displayHeight)
            dy = displayHeight - rect.bottom;

        matrix.postTranslate(dx, dy);
    }

    // 获取最小缩放比例
    private float getMinScale() {
        float sx = (float) displayWidth / imgWidth;
        float sy = (float) displayHeight / imgHeight;
        float scale = sx < sy ? sx : sy;
        if (scale > 1) {
            scale = 1f;
        }
        return scale;
    }

    // 检查约束条件，是否居中，空间显示是否合理
    private void checkView() {
        currentScale = getCurrentScale();
        if (mode == ZOOM) {
            if (currentScale < minScale) {
                matrix.setScale(minScale, minScale);
            }
            if (currentScale > maxScale) {
                matrix.set(savedMatrix);
            }
        }
        center();
    }

    // 图片当前的缩放比例
    private float getCurrentScale() {
        float[] values = new float[9];
        matrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }
}
