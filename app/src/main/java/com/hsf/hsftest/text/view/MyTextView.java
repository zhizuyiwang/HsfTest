package com.hsf.hsftest.text.view;


import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by Thinkpadx240 on 2017/8/11.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused)
            super.onWindowFocusChanged(focused);
    }

    @Override
    public boolean isFocused() {
        return true;//一直返回true，假装这个控件一直获取着焦点
    }

}
