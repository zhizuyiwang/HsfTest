package com.hsf.hsftest.design.recycler.referesh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefreshRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.tv_refresh_load)
    TextView tvRefreshLoad;
    @BindView(R.id.tv_refresh_load1)
    TextView tvRefreshLoad1;
    @BindView(R.id.tv_refresh_load2)
    TextView tvRefreshLoad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_refresh_load, R.id.tv_refresh_load1, R.id.tv_refresh_load2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh_load:
                startActivity(new Intent(this,CustomRefreshRecyclerViewActivity.class));
                break;
            case R.id.tv_refresh_load1:
                startActivity(new Intent(this,CustomSwipeRefreshLayoutActivity.class));
                break;
            case R.id.tv_refresh_load2:
                startActivity(new Intent(this,SwipeToLoadLayoutActivity.class));
                break;
        }
    }
}
