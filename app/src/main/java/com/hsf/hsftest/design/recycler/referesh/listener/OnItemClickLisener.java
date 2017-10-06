package com.hsf.hsftest.design.recycler.referesh.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/28 13:58
 */

  public interface OnItemClickLisener {
    void onItemClick(int position, int viewType, RecyclerView.ViewHolder holder, View v);
    void onItemLongClick(int position, int viewType, RecyclerView.ViewHolder holder, View v);
}
