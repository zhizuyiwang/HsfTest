package com.hsf.hsftest.design.recycler.multiitem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.bean.TypeInterf;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:43
 */


public class SecondMultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "ChatAdapter";

    private Context context;
    private LayoutInflater inflater;
    private List<TypeInterf> items = new ArrayList<>();

    public SecondMultiTypeAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==R.layout.item1) {
            View view = inflater.inflate(R.layout.item1, parent, false);
            RightHolder rightHolder = new RightHolder(view);
            return rightHolder;
        }else if (viewType == R.layout.item2){
            View v = inflater.inflate(R.layout.item2,parent,false);
            LeftHolder leftHolder = new LeftHolder(v);
            return leftHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RightHolder) {
            RightHolder rightHolder = (RightHolder) holder;
            if (items != null) {
            }
        }else if (holder instanceof LeftHolder){
            LeftHolder leftHolder = (LeftHolder) holder;
            if (items!=null){
            }
        }
    }

    @Override
    public int getItemCount() {
        if (items!=null)
            return items.size();
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public void addMsg(TypeInterf msg){
        items.add(msg);
        notifyItemInserted(items.size());
    }

    public class RightHolder extends RecyclerView.ViewHolder {
        private TextView tvChatContent;
        public RightHolder(View itemView) {
            super(itemView);
        }
    }

    public class LeftHolder extends RecyclerView.ViewHolder {
        private TextView tvChatContent;
        public LeftHolder(View itemView) {
            super(itemView);
        }
    }
}
