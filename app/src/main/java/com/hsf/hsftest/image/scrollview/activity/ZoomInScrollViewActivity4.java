package com.hsf.hsftest.image.scrollview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.scrollview.customscrollview.DampView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 hsf
 * @创建日期 2017/10/8 15:16
 * http://www.jianshu.com/p/834e522d02dc
 */

public class ZoomInScrollViewActivity4 extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.dv)
    DampView dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in_scroll_view4);
        ButterKnife.bind(this);
        dv.setImageView(iv);
    }
}
