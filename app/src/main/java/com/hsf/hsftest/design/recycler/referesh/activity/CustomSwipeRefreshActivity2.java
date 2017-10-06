package com.hsf.hsftest.design.recycler.referesh.activity;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.decoration.MySwipeRefreshDecoration;
import com.hsf.hsftest.design.recycler.referesh.listener.EndLessOnScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/29 10:47
 * http://www.jianshu.com/p/3bf125b4917d
 */

public class CustomSwipeRefreshActivity2 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private List<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_swipe_refresh2);

        initData();
        initView();

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //添加分隔线
        mRecyclerView.addItemDecoration(new MySwipeRefreshDecoration(this, MySwipeRefreshDecoration.VERTICAL_LIST));

        //为RecyclerView加载Adapter
        mRecyclerView.setAdapter(mAdapter);

        //监听SwipeRefreshLayout.OnRefreshListener
        mRefreshLayout.setOnRefreshListener(this);

        /**
         * 监听addOnScrollListener这个方法，新建我们的EndLessOnScrollListener
         * 在onLoadMore方法中去完成上拉加载的操作
         * */
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });
    }
    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData(){
        for (int i =0; i < 1; i++){
            mData.add("嘿，我是“上拉加载”生出来的"+i);
            mAdapter.notifyDataSetChanged();
        }
    }
    //初始化界面
    private void initView(){
        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.layout_swipe_refresh);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mAdapter = new MyAdapter(this,mData);

        //这个是下拉刷新出现的那个圈圈要显示的颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorRed,
                R.color.colorYellow,
                R.color.colorGreen
        );
    }
    //初始化一开始加载的数据
    private void initData(){
        mData = new ArrayList<String>();
        for (int i = 0; i < 20; i++){
            mData.add("Item"+i);
        }
    }
    @Override
    public void onRefresh() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(3000);
                updateData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }.start();

    }
    private void updateData(){
        //我在List最前面加入一条数据
        mData.add(0, "嘿，我是“下拉刷新”生出来的");
    }

    static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        //定义一个集合，接收从Activity中传递过来的数据和上下文
        private List<String> mList;
        private Context mContext;

        MyAdapter(Context context, List<String> list){
            this.mContext = context;
            this.mList = list;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
            return new MyHolder(layout);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyHolder){
                final String itemText = mList.get(position);
                ((MyHolder)holder).tv.setText(itemText);
            }
        }

        class MyHolder extends RecyclerView.ViewHolder{

            TextView tv;
            public MyHolder(View itemView) {
                super(itemView);
                tv = (TextView)itemView.findViewById(R.id.list_item);
            }
        }
    }
}
