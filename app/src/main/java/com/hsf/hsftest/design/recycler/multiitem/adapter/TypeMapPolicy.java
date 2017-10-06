package com.hsf.hsftest.design.recycler.multiitem.adapter;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:20
 */


public interface TypeMapPolicy {
    int toType(ItemViewFactory viewFactory);

    ItemViewFactory toItemView(int type);
}
