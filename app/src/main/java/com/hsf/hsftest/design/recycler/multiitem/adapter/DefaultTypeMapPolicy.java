package com.hsf.hsftest.design.recycler.multiitem.adapter;


import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:22
 */


public class DefaultTypeMapPolicy implements TypeMapPolicy {

    private List<ItemViewFactory> mTypesMapping = new ArrayList<>();

    @Override
    public int toType(ItemViewFactory item) {
        Class<?> clz = item.getClass();

        for (int i = mTypesMapping.size() - 1; i > -1; i--) {
            ItemViewFactory itemViewFactory = mTypesMapping.get(i);
            if (itemViewFactory.getClass() == clz) {
                return i;
            }
        }
        mTypesMapping.add(item);
        return mTypesMapping.size() - 1;
    }

    @Override
    public ItemViewFactory toItemView(int viewType) {
        return mTypesMapping.get(viewType);
    }
}
