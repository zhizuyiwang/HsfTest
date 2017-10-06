package com.hsf.hsftest.design.recycler.multiitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.multiitem.adapter.ItemViewFactory;
import com.hsf.hsftest.design.recycler.multiitem.adapter.MultiTypeRecyclerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultiItemActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    MultiTypeRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(this).orientation(LinearLayoutManager.VERTICAL)
                .itemsDividerWidth(1)
                .beforeFirstViewDividerWidth(1)
                .afterLastViewDividerWidth(1)
        );

        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        //设置每种布局最大缓存数量，可以不设置
        recycledViewPool.setMaxRecycledViews(0, 15);
        recycledViewPool.setMaxRecycledViews(1, 15);
        recycledViewPool.setMaxRecycledViews(2, 15);
        recycledViewPool.setMaxRecycledViews(3, 15);
        recycledViewPool.setMaxRecycledViews(4, 15);
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        mAdapter = new MultiTypeRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        List<ItemViewFactory> data = getData();
        mAdapter.setData(data);

        findViewById(R.id.but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setData(getData2());
            }
        });
    }


    public List<ItemViewFactory> getData() {
        List<ItemViewFactory> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add(new ItemView1(this, i));
        }

        for (int i = 0; i < 1; i++) {
            data.add(new ItemView2(this, "view2---------- " + i));
        }

//        for (int i = 0; i < 10; i++) {
//            data.add(new ItemView3(this, new Date(2017-1900, 1, 1 + i)));
//        }
//
        for (int i = 0; i < 8; i++) {
            data.add(new ItemView1(this, 800 + i));
        }
//
        for (int i = 0; i < 2; i++) {
            data.add(new ItemView4(this, i));
        }
//
//        for (int i = 0; i < 50; i++) {
//            data.add(new ItemView5(this, "view5//////// " + i));
//        }

        return data;
    }

    public List<ItemViewFactory> getData2() {
        List<ItemViewFactory> data = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            data.add(new ItemView3(this, new Date(2017 - 1900, 1, 1 + i)));
        }

        for (int i = 0; i < 3; i++) {
            data.add(new ItemView2(this, "view2---------- " + i));
        }


//        for (int i = 0; i < 5; i++) {
//            data.add(new ItemView1(this, i));
//        }
//


        for (int i = 0; i < 2; i++) {
            data.add(new ItemView4(this, i));
        }

        for (int i = 0; i < 5; i++) {
            data.add(new ItemView5(this, "view5//////// " + i));
        }
        for (int i = 0; i < 8; i++) {
            data.add(new ItemView1(this, 800 + i));
        }

        return data;
    }
}
