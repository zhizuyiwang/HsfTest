package com.hsf.hsftest.design.recycler.referesh.listener;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/28 13:59
 */

public interface OnRecyclerStatusChangeListener {

    public void onRefresh();

    public void onLoadMore();

    public void startRefresh();

    public void refreshComplete();
}
