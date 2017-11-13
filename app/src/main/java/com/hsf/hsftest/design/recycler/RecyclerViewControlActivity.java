package com.hsf.hsftest.design.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.multiitem.MultiItemActivity;
import com.hsf.hsftest.design.recycler.referesh.activity.RefreshRecyclerActivity;
import com.hsf.hsftest.design.recycler.stickheader.StickyHeaderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/9/5 11:58
 * recycleView的用法控制类
 */

public class RecyclerViewControlActivity extends AppCompatActivity {

    @BindView(R.id.tv_recycler_flush_loading)
    TextView tvRecyclerFlushLoading;
    @BindView(R.id.tv_recycler_layout_manager)
    TextView tvRecyclerLayoutManager;
    @BindView(R.id.tv_recycler_suspension)
    TextView tvRecyclerSuspension;
    @BindView(R.id.tv_recycler_multi_item)
    TextView tvRecyclerMultiItem;
    @BindView(R.id.tv_recycler_down_load)
    TextView tvRecyclerDownLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_control);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_recycler_flush_loading, R.id.tv_recycler_layout_manager, R.id.tv_recycler_suspension,
            R.id.tv_recycler_multi_item, R.id.tv_recycler_down_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_recycler_flush_loading:
                startActivity(new Intent(this, RefreshRecyclerActivity.class));
                break;
            case R.id.tv_recycler_layout_manager:

                break;
            case R.id.tv_recycler_suspension:
                startActivity(new Intent(this, StickyHeaderActivity.class));
                break;
            case R.id.tv_recycler_multi_item:
                startActivity(new Intent(this, MultiItemActivity.class));

                break;
            case R.id.tv_recycler_down_load:
                break;
        }
    }
}
