package com.hsf.hsftest.design.recycler.referesh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.adapter.TestRecyclerViewAdapter;
import com.hsf.hsftest.design.recycler.referesh.custom.BaseRefreshRecyclerView;
import com.hsf.hsftest.design.recycler.referesh.decoration.SimpleItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/14 11:27
 * http://www.jianshu.com/p/dcd46b0e2cf8
 */

public class CustomRecyclerViewActivity2 extends AppCompatActivity {
    private BaseRefreshRecyclerView rcv_test;
    private TestRecyclerViewAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler_view2);
        rcv_test = (BaseRefreshRecyclerView) findViewById(R.id.rcv_test);
        madapter = new TestRecyclerViewAdapter();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcv_test.setLayoutManager(staggeredGridLayoutManager);
        rcv_test.addItemDecoration(new SimpleItemDecoration(20,3));
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        ArrayList list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        madapter.setData(list);
        rcv_test.setAdapter(madapter);
        rcv_test.setOnRefreshAndLoadMoreListener(new BaseRefreshRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(CustomRecyclerViewActivity2.this, "Refreshing", Toast.LENGTH_SHORT).show();
                rcv_test.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rcv_test.completeRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                rcv_test.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List data = madapter.getData();
                        for (int i = 0; i < 10; i++) {
                            data.add(i * 1000);
                        }
                        madapter.setData(data);
                        madapter.notifyDataSetChanged();
                        rcv_test.completeLoadMore();
                    }
                }, 3000);
            }
        });

    }

    /**
     * 模拟数据回调，刷新和加载更多共用一个回调。completeRefresh()与completeLoadMore()
     * 需要分刷新和加载更多不同情况分别调用
     */
    public class dataCallBack {
        private boolean isLoadMore;

        void onSuccess(){
            rcv_test.completeLoading();//必须调用，重置状态。
            List data = new ArrayList();//解析后的数据。
            if (data != null && data.size() > 0) {
                if (isLoadMore) {
                    List products = rcv_test.getData();
                    if (products != null) {
                        int size = products.size();
                        products.addAll(data);
                        madapter.notifyItemInserted(size + 1);
                    } else {
                        madapter.setData(data);
                        madapter.notifyDataSetChanged();
                    }
                    rcv_test.completeLoadMore();
                } else {
                    madapter.setData(data);
                    madapter.notifyDataSetChanged();
                    rcv_test.completeRefresh();
                }


            } else {
                rcv_test.noMoreData();
            }
        }
        void onFailed(){
            rcv_test.completeLoading();//必须调用
            madapter.setFooterRefreshFailState();
        }
    }
}
