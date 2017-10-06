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

public class CustomSwipeRefreshLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tv_custom_swipe1)
    TextView tvCustomSwipe1;
    @BindView(R.id.tv_custom_swipe2)
    TextView tvCustomSwipe2;
    @BindView(R.id.tv_custom_swipe3)
    TextView tvCustomSwipe3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view3);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_custom_swipe1, R.id.tv_custom_swipe2,R.id.tv_custom_swipe3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_custom_swipe1:
                startActivity(new Intent(this, CustomSwipeRefreshActivity1.class));
                break;
            case R.id.tv_custom_swipe2:
                startActivity(new Intent(this, CustomSwipeRefreshActivity2.class));
                break;
            case R.id.tv_custom_swipe3:
                startActivity(new Intent(this,CustomSwipeRefreshActivity3.class));
                break;
        }
    }
}
