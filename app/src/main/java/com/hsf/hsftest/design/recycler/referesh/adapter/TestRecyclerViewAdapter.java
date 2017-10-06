package com.hsf.hsftest.design.recycler.referesh.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hsf.hsftest.R;

/**
 * Created by super-zuo on 16-7-5.
 */
public class TestRecyclerViewAdapter extends BaseRefreshRecyclerViewAdapter {
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int i) {
        TestViewHolder testViewHolder = (TestViewHolder) holder;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) testViewHolder.iv.getLayoutParams();
        int height = 0;
        if (i*10>200){
            height = 500-i;
        }else{
            height = 300+i*5;
        }
        params.height = height;
        testViewHolder.iv.setLayoutParams(params);
    }


    static class TestViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public TestViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
