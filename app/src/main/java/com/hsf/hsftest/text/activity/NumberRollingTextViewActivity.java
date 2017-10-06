package com.hsf.hsftest.text.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.TitleView;
import com.hsf.hsftest.text.view.NumberRollingView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/5 17:20
 * 自定义数字滚动动画的TextView
 */

public class NumberRollingTextViewActivity extends AppCompatActivity {

    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.tv_money)
    NumberRollingView tvMoney;
    @BindView(R.id.tv_num)
    NumberRollingView tvNum;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_rolling_text_view);
        ButterKnife.bind(this);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tvMoney.setUseCommaFormat(true);
                tvNum.setUseCommaFormat(true);
                tvNum.setFrameNum(50);
                String money = (9666.86+(++i))+"";
                tvMoney.setContent(money);
                String num = (9666+(++i))+"";
                tvNum.setContent(num);
                srlRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srlRefresh.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void initData() {
        tvMoney.setContent("9686.86");
        tvNum.setContent("9686");
    }

    private void initView() {
        titleView.setAppTitle("自定义数字滚动动画的TextView");
        titleView.setLeftImageVisibility(View.GONE);
        srlRefresh.setColorSchemeColors(Color.parseColor("#ff33b5e5"));
    }

}
