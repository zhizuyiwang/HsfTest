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

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:21
 */

public class ItemView4 extends ItemViewFactory<Integer, ItemView4.Item4VH> {

    private static final String TAG ="ItemView4" ;

    public ItemView4(Context context, Integer data) {
        super(context, data);
    }

    @Override
    public Item4VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder:..... 4");
        return new Item4VH(LayoutInflater.from(context).inflate(R.layout.item1, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item4VH holder, final Integer data) {
        holder.mTextView.setText(data + "");
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(data + 10000);
            }
        });
    }


    static class Item4VH extends RecyclerView.ViewHolder {

        TextView mTextView;

        public Item4VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
