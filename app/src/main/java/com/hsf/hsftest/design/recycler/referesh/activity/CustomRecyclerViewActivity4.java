package com.hsf.hsftest.design.recycler.referesh.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.adapter.BaseRecyPRAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/29 10:53
 * http://blog.csdn.net/desireyaoo/article/details/77585079
 */

public class CustomRecyclerViewActivity4 extends AppCompatActivity {

    private static final String TAG = "RecyActivity";
    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<>();
    private MyRecyViewAdapter mAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loading4);

        init();
    }

    private void init() {
        initView();
        initData();


    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //创建一个LinearLayoutManager对象
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //创建adapter对象
        mAdapter = new MyRecyViewAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(list);//设置数据
    }

    //初始化数据
    private void initData() {
        for (int i = 0; i < 9; i++) {
            list.add("DATA---------->" + i);
        }
    }
    //初始化监听
    private void initListener() {
        //加载更多回调监听
        mAdapter.setLoadMoreDataListener(new BaseRecyPRAdapter.LoadMoreDataListener() {
            @Override
            public void loadMoreData() {
                //加入null值此时adapter会判断item的type
                Log.e(TAG, "loadMoreData--->");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (list.size() < 30) {
                            for (int i = 0; i < 5; i++) {
                                list.add("LOAD MORE DATA---------->" + i);
                            }
                            mAdapter.stopLoadMore();
                        } else {
                            mAdapter.loadComplete();
                        }
                    }
                }, 1000);
            }
        });
    }
    class MyRecyViewAdapter extends BaseRecyPRAdapter<String> {

        public MyRecyViewAdapter(Context context, RecyclerView recyclerView) {
            super(context, recyclerView);
        }

        @Override
        public int getLayoutId(int type) {
            return R.layout.item_view;
        }

        @Override
        public void onBindData(BaseViewHolder holder, int position) {
            Log.e(TAG, "onBindData--->" + position);
            if (mDataList == null || mDataList.isEmpty())
                return;
            TextView tv = (TextView) holder.getView(R.id.tv_name);
            if (tv != null) {
                tv.setText("DATA--------->" + position);
            }
        }
    }
}
