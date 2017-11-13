package com.hsf.hsftest.dialog.pagedialog;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/11/9 16:42
 */


public class NoticePagerAdapter extends PagerAdapter{
    private List<View> views;

    public NoticePagerAdapter(List<View> views){
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position), 0);  //添加页卡
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));   //删除页卡
    }
}
