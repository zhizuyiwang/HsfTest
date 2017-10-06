package com.hsf.hsftest.image.scalelayout.scaleViewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.hsf.hsftest.image.scalelayout.ScaleLayout;


/**
  * @author deadline
  * @time 2016/10/12.
 */
public class ViewPagerScaleLayout extends ScaleLayout {

    ViewPager viewPager;

    public ViewPagerScaleLayout(Context context) {
        this(context, null);
    }

    public ViewPagerScaleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerScaleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(mCenterView instanceof ViewPager) {
            viewPager = (ViewPager) mCenterView;
        }
    }

    @Override
    public void doSetCenterView(float scale) {
        if(viewPager != null) {
            viewPager.setPivotX(getCenterViewPivotX());
            viewPager.setPivotY(getCenterViewPivotY());

            viewPager.setScaleY(scale);
        }
    }
}
