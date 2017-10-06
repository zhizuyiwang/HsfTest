package com.hsf.hsftest.design.recycler.referesh.view;

import android.content.Context;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;


public class LoaderMoreView extends android.support.v7.widget.AppCompatTextView implements SwipeTrigger, SwipeLoadMoreTrigger {

    public LoaderMoreView(Context context) {
        super(context);
    }

    public LoaderMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoaderMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
        setText("正在拼命加载数据…");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText("释放加载");
            } else {
                setText("上拉加载更多");
            }
        } else {
            setText("加载成功");
        }
    }

    @Override
    public void onRelease() {
        setText("加载更多");
    }

    @Override
    public void onComplete() {
        setText("加载完成");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
