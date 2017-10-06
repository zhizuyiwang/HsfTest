package com.hsf.hsftest.design.recycler.referesh.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.referesh.ui.BaiDuRefreshActivity;
import com.hsf.hsftest.design.recycler.referesh.ui.ElmRefreshActivity;
import com.hsf.hsftest.design.recycler.referesh.ui.JDRefreshActivity;
import com.hsf.hsftest.design.recycler.referesh.ui.MTWMRefreshActivity;
import com.hsf.hsftest.design.recycler.referesh.ui.TianMaoRefreshActivity;
import com.hsf.hsftest.design.recycler.referesh.ui.WeatherRefreshActivity;
import com.hsf.hsftest.design.recycler.referesh.ui.WeiboRefreshActivity;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/19 15:16
 *
 */

public class SwipeToLoadActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_load_layout1);
    }
    public void click01(View view) {
        startActivity(new Intent(this, ElmRefreshActivity.class));
    }

    public void click02(View view) {
        startActivity(new Intent(this, WeatherRefreshActivity.class));
    }

    public void click03(View view) {
        startActivity(new Intent(this, WeiboRefreshActivity.class));
    }

    public void click04(View view) {
        startActivity(new Intent(this, JDRefreshActivity.class));
    }
    public void click05(View view) {
        startActivity(new Intent(this, BaiDuRefreshActivity.class));
    }
    public void click06(View view) {
        startActivity(new Intent(this, MTWMRefreshActivity.class));
    }
    public void click07(View view) {
        startActivity(new Intent(this, TianMaoRefreshActivity.class));
    }
}
