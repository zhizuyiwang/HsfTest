package com.hsf.hsftest.image.imagezoom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.util.DisplayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpacePageActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    private Bitmap bitmap;
    private String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479174352&di=b4ec34b54effa54f3c5be76e94381b44&src=http://a.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11daf25f19bc12c8fcc3ce2d46.jpg";
    private int imgWidth;
    private int imgHeight;
    private Matrix matrix = new Matrix();
    private float minScale = 1f;
    private float maxScale = 10f;
    // 获取屏幕分辨率。以480*320为例
    private int displayHeight;
    private int displayWidth;
    private float currentScale = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_page);
        ButterKnife.bind(this);
        displayHeight = DisplayUtils.getDisplayHight(getApplicationContext())/4;
        displayWidth = DisplayUtils.getDisplayWidth(getApplicationContext())/4;

        iv.setOnTouchListener(new MulitPointTouchListener(iv));
        new Thread(){
            @Override
            public void run() {
                super.run();
                bitmap = returnBitMap(url);

                iv.post(new Runnable() {
                    @Override
                    public void run() {
                        imgWidth = bitmap.getWidth();
                        imgHeight = bitmap.getHeight();
                        minScale = getMinScale();
                        matrix.setScale(minScale, minScale);
                        center();
                        iv.setImageMatrix(matrix);
                        iv.setImageBitmap(bitmap);
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
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(bitmap!=null){
            if(!bitmap.isRecycled()){
                bitmap.recycle();   //回收图片所占的内存
                bitmap=null;
                System.gc();  //提醒系统及时回收
            }
        }
    }

    public class MulitPointTouchListener implements View.OnTouchListener {
        static final int NONE = 0;
        static final int DRAG = 1;
        static final int ZOOM = 2;
        public ImageView image;
        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();
        int mode = NONE;
        PointF start = new PointF();
        PointF mid = new PointF();
        float oldDist = 1f;
        public MulitPointTouchListener(ImageView image) {
            super();
            this.image = image;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            this.image.setScaleType(ImageView.ScaleType.MATRIX);
            ImageView view = (ImageView) v;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    Log.w("FLAG", "ACTION_DOWN");
                    matrix.set(view.getImageMatrix());
                    savedMatrix.set(matrix);
                    start.set(event.getX(), event.getY());
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.w("FLAG", "ACTION_POINTER_DOWN");
                    oldDist = spacing(event);
                    if (oldDist > 10f) {
                        savedMatrix.set(matrix);
                        midPoint(mid, event);
                        mode = ZOOM;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.w("FLAG", "ACTION_UP");
                case MotionEvent.ACTION_POINTER_UP:
                    Log.w("FLAG", "ACTION_POINTER_UP");
                    mode = NONE;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.w("FLAG", "ACTION_MOVE");
                    Log.e("FLAG","X=="+event.getX()+"");
                    Log.e("FLAG","RawX=="+event.getRawX()+"");
                    if (mode == DRAG) {
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - start.x, event.getY()
                                - start.y);
                    } else if (mode == ZOOM) {
                        float newDist = spacing(event);
                        if (newDist > 10f) {
                            matrix.set(savedMatrix);
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                        }
                    }
                    break;
            }
            view.setImageMatrix(matrix);
           // checkView(mode,savedMatrix);
            return true;
        }
        private float spacing(MotionEvent event) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        }

        private void midPoint(PointF point, MotionEvent event) {
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
        }
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
    private void checkView(int mode, Matrix savedMatrix) {
        currentScale = getCurrentScale();
        if (mode == 2) {
            if (currentScale < minScale) {
                matrix.setScale(minScale, minScale);
            }
            if (currentScale > maxScale) {
                matrix.set(savedMatrix);
            }
        }
        center();
        //iv.setImageMatrix(matrix);
    }
    // 图片当前的缩放比例
    private float getCurrentScale() {
        float[] values = new float[9];
        matrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }
}
