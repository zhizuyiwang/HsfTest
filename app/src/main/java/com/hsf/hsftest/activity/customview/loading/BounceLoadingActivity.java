package com.hsf.hsftest.activity.customview.loading;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.hsftest.R;

public class BounceLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_loading);
        BounceLoadingView loadingView = (BounceLoadingView) findViewById(R.id.view);
        loadingView.addBitmap(R.mipmap.v4);
        loadingView.addBitmap(R.mipmap.v5);
        loadingView.addBitmap(R.mipmap.v6);
        loadingView.addBitmap(R.mipmap.v7);
        loadingView.addBitmap(R.mipmap.v8);
        loadingView.addBitmap(R.mipmap.v9);
        loadingView.setShadowColor(Color.LTGRAY);
        loadingView.setDuration(700);
        loadingView.start();
    }
}
