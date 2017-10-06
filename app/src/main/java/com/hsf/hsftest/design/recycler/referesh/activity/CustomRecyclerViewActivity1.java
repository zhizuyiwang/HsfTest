package com.hsf.hsftest.design.recycler.referesh.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.decoration.MyItemDecoration;
import com.hsf.hsftest.design.recycler.referesh.custom.MyRecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewActivity1 extends AppCompatActivity {

    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view2);

        final MyRecyclerView recyclerview = (MyRecyclerView) findViewById(R.id.my_recycler_view);
        //设置LayoutManager
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new MyItemDecoration());

        final MyAdapter myAdapter=new MyAdapter();
        for(int i=0;i<15;i++){
            myAdapter.data.add("先定个能实现的小目标,比方说先挣它一个亿"+i);
        }

        recyclerview.setAdapter(myAdapter);

        recyclerview.setMyRecyclerViewListener(new MyRecyclerView.MyRecyclerViewListener() {
            @Override
            public void onRefresh() {
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.setRefreshComplete();;
                    }
                },1500);
            }

            @Override
            public void onLoadMore() {
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<5;i++){
                            myAdapter.data.add(i+"ok");
                        }
                        myAdapter.notifyDataSetChanged();
                        recyclerview.setLoadMoreComplete();
                    }
                },1000);
            }
        });

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        public ArrayList<String> data=new ArrayList<>();
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View rootView= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item,parent,false);

            return new MyViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.txt.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt= (TextView) itemView.findViewById(R.id.txt);
        }
    }
}
