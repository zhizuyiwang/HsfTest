package com.hsf.hsftest.design.recycler.referesh.listener;
/**
 *@作者 hsf
 *
 *@创建日期 2017/9/28 13:58
 */

public abstract class OnRecyclerChangeListener {

    public abstract void onRefresh();

    public abstract void onLoadMore();

    public void startRefresh() {
    }
    public void refreshComplete() {
    }
}
