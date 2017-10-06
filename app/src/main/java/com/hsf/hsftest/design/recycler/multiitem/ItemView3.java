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

import java.util.Date;


/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:21
 */


public class ItemView3 extends ItemViewFactory<Date, ItemView3.Item3VH> {


    public ItemView3(Context context, Date data) {
        super(context, data);
    }

    @Override
    public Item3VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(MultiTypeRecyclerAdapter.TAG, "onCreateViewHolder:++++ 3");
        return new Item3VH(LayoutInflater.from(context).inflate(R.layout.item3, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item3VH holder, final Date data) {
        holder.mTextView.setText(data.toString());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(new Date(data.getYear(), data.getMonth(), data.getDate() + 1));
            }
        });
    }

    static class Item3VH extends RecyclerView.ViewHolder {

        TextView mTextView;

        public Item3VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
