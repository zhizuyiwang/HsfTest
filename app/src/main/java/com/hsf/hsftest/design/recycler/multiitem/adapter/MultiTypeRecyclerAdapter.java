package com.hsf.hsftest.design.recycler.multiitem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:20
 */

public class MultiTypeRecyclerAdapter extends RecyclerView.Adapter {
    public static String TAG = MultiTypeRecyclerAdapter.class.getName();
    private List<ItemViewFactory> mItemViewList = new ArrayList<>();
    private TypeMapPolicy mTypeMapPolicy = new DefaultTypeMapPolicy();

    @Override
    public int getItemViewType(int position) {
        ItemViewFactory item = mItemViewList.get(position);
        return mTypeMapPolicy.toType(item);
    }

    public void setTypeMapPolicy(TypeMapPolicy typeMapPolicy) {
        mTypeMapPolicy = typeMapPolicy;
    }

    public void setData(List<ItemViewFactory> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        mItemViewList.clear();

        appendData(datas);
    }

    public void appendData(List<ItemViewFactory> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        for (ItemViewFactory item : datas) {
            if (item == null) {
                continue;
            }
            mItemViewList.add(item);
            item.attachAdapter(this);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeMapPolicy.toItemView(viewType).innerCreateVH(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewFactory viewFactory = mItemViewList.get(position);
        viewFactory.innerBindVH(holder, position);
    }

    @Override
    public int getItemCount() {
        return mItemViewList.size();
    }


}
