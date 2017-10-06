package com.hsf.hsftest.design.recycler.referesh.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hsf.hsftest.R;

/**
 * 美团外面刷新界面
 */
public class MTWMRefreshActivity extends AppCompatActivity implements OnRefreshListener {

    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtwm_refresh);

        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);

        swipeToLoadLayout.setOnRefreshListener(this);

        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 100);

    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
