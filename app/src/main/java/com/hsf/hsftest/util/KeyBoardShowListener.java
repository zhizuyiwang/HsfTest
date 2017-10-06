package com.hsf.hsftest.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


/**
 * Created by Jevons on 2017/8/2.
 * 描述:软键盘监听工具类
 */

public class KeyBoardShowListener {
    private Context ctx;
    private static KeyBoardShowListener mkeyBoardShowListener;

    private KeyBoardShowListener(Context ctx) {
        this.ctx = ctx;
    }

    public static KeyBoardShowListener getInstance(Context ctx){
        if(mkeyBoardShowListener == null){
            mkeyBoardShowListener = new KeyBoardShowListener(ctx);
        }
        return mkeyBoardShowListener;
    }

    OnKeyboardVisibilityListener keyboardListener;

    public OnKeyboardVisibilityListener getKeyboardListener() {
        return keyboardListener;
    }

    public interface OnKeyboardVisibilityListener {
        void onVisibilityChanged(boolean visible);
    }

    /**
     * 方法一
     * 注册布局变化监听
     * @param listener
     * @param activity
     */
    public void setKeyboardListener(final OnKeyboardVisibilityListener listener, Activity activity) {
        final View activityRootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean wasOpened;
            private final int DefaultKeyboardDP = 100;
            private final int EstimatedKeyboardDP = DefaultKeyboardDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            //判断窗口可见区域大小
            private final Rect r = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, activityRootView.getResources().getDisplayMetrics());
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                if (isShown == wasOpened) {
                    Log.e("Keyboard state", "Ignoring global layout change...");
                    return;
                }
                wasOpened = isShown;
                listener.onVisibilityChanged(isShown);
            }
        });
    }

    /**
     * 注册的监听在不使用时需要调用removeOnGlobalLayoutListener或removeGlobalOnLayoutListener来移除监听，不然可能会导致内存泄露。
     * 通常可以在Activity的onCreate()方法中注册监听，在onDestory()方法中移除监听。
     * @param mLayoutChangeListener
     * @param activity
     */
    public void removeKeyboardListener( ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener,Activity activity){
        View activityRootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        //移除布局变化监听
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);
        } else {
            activityRootView.getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutChangeListener);
        }
    }

    /**
     * 方法二
     * 注册布局变化监听
     * @param listener
     * @param activity
     * http://blog.csdn.net/ccpat/article/details/46730771#t4
     */
    public void setSoftKeyboardChangedListener(final OnKeyboardVisibilityListener listener, final Activity activity) {
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               //判断窗口可见区域大小
                Rect r = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int screenHeight = ViewUtils.getScreenWH1(activity)[1];
                int heightDifference =  - (r.bottom - r.top);
                boolean isKeyboardShowing = heightDifference > screenHeight/3;
                listener.onVisibilityChanged(isKeyboardShowing);

            }
        });
    }

}

