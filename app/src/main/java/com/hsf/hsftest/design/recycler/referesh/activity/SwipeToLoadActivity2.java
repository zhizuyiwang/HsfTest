package com.hsf.hsftest.design.recycler.referesh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.adapter.HomeAdapter;
import com.hsf.hsftest.design.recycler.referesh.decoration.DividerItemDecoration;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/29 10:46
 * http://www.jb51.net/article/84634.htm
 */

public class SwipeToLoadActivity2 extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {
    private RecyclerView mRecycleView;
    SwipeToLoadLayout swipeToLoadLayout;
    private HomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_load2);

        swipeToLoadLayout = ((SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout));

        mRecycleView = ((RecyclerView) findViewById(R.id.swipe_target));

        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int extent = recyclerView.computeVerticalScrollExtent();
                int range = recyclerView.computeVerticalScrollRange();
                Log.e("extent",extent+"");
                Log.e("range",range+"");
                if (range > extent) {
                    swipeToLoadLayout.setLoadMoreEnabled(true);
                } else {
                    swipeToLoadLayout.setLoadMoreEnabled(false);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

        adapter = new HomeAdapter();

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //添加分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));

        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(adapter);

        adapter.refresh();

        /**
         * 设置下拉刷新和上拉加载监听
         */
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setLoadMoreEnabled(false);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.refresh();
                swipeToLoadLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.add();
                swipeToLoadLayout.setLoadingMore(false);
            }
        },2000);
    }
}
