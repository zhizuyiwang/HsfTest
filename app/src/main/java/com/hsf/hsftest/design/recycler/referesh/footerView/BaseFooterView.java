package com.hsf.hsftest.design.recycler.referesh.footerView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.hsf.hsftest.design.recycler.referesh.listener.FooterViewListener;

/**
 * @auther deadline
 */
public abstract class BaseFooterView extends FrameLayout implements FooterViewListener {

    public BaseFooterView(Context context) {
        super(context);
    }

    public BaseFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
