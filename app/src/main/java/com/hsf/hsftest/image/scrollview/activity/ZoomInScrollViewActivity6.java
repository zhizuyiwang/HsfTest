package com.hsf.hsftest.image.scrollview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.scrollview.customscrollview.FlexibleScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZoomInScrollViewActivity6 extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.flexible_scroll_vew)
    FlexibleScrollView flexibleScrollVew;
    @BindView(R.id.back)
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in_scroll_view6);
        ButterKnife.bind(this);
        flexibleScrollVew.bindActionBar(findViewById(R.id.custom_action_bar));
        flexibleScrollVew.setHeaderView(findViewById(R.id.flexible_header_view));
    }


}
