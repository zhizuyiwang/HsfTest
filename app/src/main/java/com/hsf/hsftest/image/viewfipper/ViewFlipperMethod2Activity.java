package com.hsf.hsftest.image.viewfipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.hsf.hsftest.R;

public class ViewFlipperMethod2Activity extends AppCompatActivity {
    private ViewFlipperMethod2Activity mActivity = null;
    private final static int MIN_MOVE = 200;//最小距离

    private ViewFlipper mVfHelp;

    private int[] resId = {R.drawable.ic_help_view_1, R.drawable.ic_help_view_2, R.drawable.ic_help_view_3, R.drawable.ic_help_view_4};
    private MyGestureListener mgListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_two);
        setContentView(R.layout.activity_method_two);
        mActivity = this;
        initView();
        initData();

    }
    private void initView() {
        mVfHelp = (ViewFlipper) findViewById(R.id.vf_help);
    }

    private void initData() {
        //实例化SimpleOnGestureListener与GestureDetector对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(mActivity, mgListener);

        //动态添加子View
        for (int i = 0; i < resId.length; i++) {
            mVfHelp.addView(getImageView(resId[i]));
        }
    }

    /**
     * @Description 重写onTouchEvent触发MyGestureListener里的方法
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    /**
     * @Description 自定义一个View类下的GestureDetector
     */
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if (e1.getX() - e2.getX() > MIN_MOVE) {
                mVfHelp.setInAnimation(mActivity, R.anim.right_in);
                mVfHelp.setOutAnimation(mActivity, R.anim.left_out);
                mVfHelp.showNext();
            } else if (e2.getX() - e1.getX() > MIN_MOVE) {
                mVfHelp.setInAnimation(mActivity, R.anim.left_in);
                mVfHelp.setOutAnimation(mActivity, R.anim.right_out);
                mVfHelp.showPrevious();
            }
            return true;
        }
    }
    private ImageView getImageView(int resId) {
        ImageView img = new ImageView(this);
        img.setBackgroundResource(resId);
        return img;
    }
}
