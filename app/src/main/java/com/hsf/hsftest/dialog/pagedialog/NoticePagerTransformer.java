package com.hsf.hsftest.dialog.pagedialog;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 *@作者 hsf
 *
 *@创建日期 2017/11/9 16:43
 */


public class NoticePagerTransformer implements ViewPager.PageTransformer{
    private static final float ROX_MAX = 20.0f;
    private float mRox;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {    //页面不可见

            ViewCompat.setRotation(page, 0);
        } else if (position < 0) { //页面可见，页面的左边界已经画出屏幕

            mRox = (position * ROX_MAX);
            ViewCompat.setPivotX(page, page.getMeasuredWidth());
            ViewCompat.setPivotY(page, page.getMeasuredHeight());
            ViewCompat.setRotation(page, mRox);

        } else if (position < 1) {  //页面可见，页面的左边界已经进入屏幕

            mRox = (position * ROX_MAX);
            ViewCompat.setPivotX(page, 0);
            ViewCompat.setPivotY(page, page.getMeasuredHeight());
            ViewCompat.setRotation(page, mRox);

        } else {  //页面不可见

            ViewCompat.setRotation(page, 0);
        }

    }
}
