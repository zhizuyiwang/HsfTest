package com.hsf.hsftest.design.recycler.referesh.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.ILoadMoreViewFactory;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.hsf.hsftest.MainActivity;
import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.adapter.RvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/28 17:42
 * https://github.com/Chanven/CommonPullToRefresh
 */

public class CustomRecyclerViewActivity5 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //支持下拉刷新的ViewGroup
    private PtrClassicFrameLayout mPtrFrame;
    //List数据
    private List<String> title = new ArrayList<>();
    //RecyclerView自定义Adapter
    private RvAdapter adapter;
    //添加Header和Footer的封装类
    private RecyclerAdapterWithHF mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler_view5);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RvAdapter(CustomRecyclerViewActivity5.this, title);

        mAdapter = new RecyclerAdapterWithHF(adapter);
        mRecyclerView.setAdapter(mAdapter);

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        //下拉刷新支持时间
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        //下拉刷新一些设置 详情参考文档
        //1、阻尼系数,默认: 1.7f，越大，感觉下拉时越吃力。
        mPtrFrame.setResistance(1.7f);
        //2、触发刷新时移动的位置比例,默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作。
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        //3、回弹延时,默认 200ms，回弹到刷新高度所用时间.
        mPtrFrame.setDurationToClose(200);
        //4、头部回弹时间
        mPtrFrame.setDurationToCloseHeader(1000);
        //5、刷新时保持头部，默认值 true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        //6、下拉刷新 / 释放刷新，默认为释放刷新，即false
        mPtrFrame.setPullToRefresh(false);

        //上拉加载配置
        //是否需要加载更多 默认false
        mPtrFrame.setLoadMoreEnable(false);
        mPtrFrame.setAutoLoadMoreEnable(true);
        //Header、Footer样式:
       // Header	实现接口PtrUIHandler，已有默认实现PtrClassicDefaultHeader，并通过PtrFrameLayout.setHeaderView(View header)设置
       // Footer	实现接口ILoadMoreViewFactory，已有默认实现DefaultLoadMoreViewFooter，并通过PtrFrameLayout.setFooterView(ILoadMoreViewFactory factory)设置

        //进入Activity就进行自动下拉刷新
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
        //下拉刷新
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                title.clear();
                //模拟数据
                for (int i = 0; i <= 50; i++) {
                    title.add(String.valueOf(i));
                    mPtrFrame.setLoadMoreEnable(true);
                }
                //模拟联网 延迟更新列表
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.refreshComplete();

                    }
                }, 1000);
            }
        });
        //上拉加载
        mPtrFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //模拟联网延迟更新数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //模拟数据
                        for (int i = 0; i <= 5; i++) {
                            title.add(String.valueOf(i));
                        }
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.loadMoreComplete(false);
                        Toast.makeText(CustomRecyclerViewActivity5.this, "load more complete", Toast.LENGTH_SHORT)
                                .show();
                    }
                }, 1000);
            }
        });

    }
    class FooterView implements ILoadMoreViewFactory {

        @Override
        public ILoadMoreView madeLoadMoreView() {

            return new ILoadMoreView() {
                @Override
                public void init(FootViewAdder footViewHolder, View.OnClickListener onClickLoadMoreListener) {

                }

                @Override
                public void showNormal() {

                }

                @Override
                public void showNomore() {

                }

                @Override
                public void showLoading() {

                }

                @Override
                public void showFail(Exception e) {

                }

                @Override
                public void setFooterVisibility(boolean isVisible) {

                }
            };
        }
    }
}
