package com.hsf.hsftest.design.recycler.multiitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.multiitem.adapter.ItemViewFactory;
import com.hsf.hsftest.design.recycler.multiitem.adapter.MultiTypeRecyclerAdapter;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:21
 */


public class ItemView2 extends ItemViewFactory<String, ItemView2.Item2VH> {

    public ItemView2(Context context, String data) {
        super(context, data);
    }

    @Override
    public Item2VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(MultiTypeRecyclerAdapter.TAG, "onCreateViewHolder:----------- 2");
        return new Item2VH(LayoutInflater.from(context).inflate(R.layout.item2, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item2VH holder, final String data) {
        holder.mTextView.setText(data);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh("change..." + data);
            }
        });
    }

    static class Item2VH extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item2VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
