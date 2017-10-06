package com.hsf.hsftest.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.hsf.hsftest.R;
import com.hsf.hsftest.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/8/23 11:49
 * View的工作原理用法
 */

public class MotionEventActivity extends AppCompatActivity {
    private static final String TAG = "MotionEventActivity";
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
        ButterKnife.bind(this);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String actionName = Utils.getActionName(event);
        Log.d(TAG, "onTouchEvent(start) :" + actionName);
        boolean ret = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent( end ) :" + actionName + ", ret=" + ret);
        return ret;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        String actionName = Utils.getActionName(event);
        Log.d(TAG, "dispatchTouchEvent(start) :" + actionName);
        boolean ret = super.dispatchTouchEvent(event);
        Log.d(TAG, "dispatchTouchEvent( end ) :" + actionName + ", ret=" + ret);
        return ret;
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {

    }
}
