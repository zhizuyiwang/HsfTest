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

public class SwipeToLoadLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tv_swipe_to_load)
    TextView tvSwipeToLoad;
    @BindView(R.id.tv_swipe_to_load1)
    TextView tvSwipeToLoad1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_swipe_to_load, R.id.tv_swipe_to_load1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_swipe_to_load:
                startActivity(new Intent(this,SwipeToLoadActivity1.class));
                break;
            case R.id.tv_swipe_to_load1:
                startActivity(new Intent(this,SwipeToLoadActivity2.class));
                break;
        }
    }
}
