package com.hsf.hsftest.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.hsf.hsftest.util.Utils;

/**
 * Created by Thinkpadx240 on 2017/8/15.
 */

public class MyViewGroup extends LinearLayout {
    private static final String TAG = "##HSF-MyViewGroup";

    public MyViewGroup(Context context){
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        String actionName = Utils.getActionName(event);
        Log.d(TAG, "dispatchTouchEvent(start) :"+actionName);
        boolean ret = super.dispatchTouchEvent(event);
        Log.d(TAG, "dispatchTouchEvent( end ) :"+actionName+", ret="+ret);
        return ret;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String actionName = Utils.getActionName(event);
        Log.d(TAG, "onTouchEvent(start) :"+actionName);
        boolean ret = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent( end ) :"+actionName+", ret="+ret);
        return ret;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        String actionName = Utils.getActionName(event);
        Log.d(TAG, "onInterceptTouchEvent(start) :"+actionName);
        boolean ret = super.onInterceptTouchEvent(event);
        Log.d(TAG, "onInterceptTouchEvent( end ) :"+actionName+", ret="+ret);
        return ret;
    }
}