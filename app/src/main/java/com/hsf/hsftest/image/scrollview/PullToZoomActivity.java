package com.hsf.hsftest.image.scrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity1;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity2;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity3;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity4;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity5;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity6;
import com.hsf.hsftest.image.scrollview.activity.ZoomInScrollViewActivity7;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PullToZoomActivity extends AppCompatActivity {

    @BindView(R.id.tv_custom_scroll_view1)
    TextView tvCustomScrollView1;
    @BindView(R.id.tv_custom_scroll_view2)
    TextView tvCustomScrollView2;
    @BindView(R.id.tv_custom_scroll_view3)
    TextView tvCustomScrollView3;
    @BindView(R.id.tv_custom_scroll_view4)
    TextView tvCustomScrollView4;
    @BindView(R.id.tv_custom_scroll_view5)
    TextView tvCustomScrollView5;
    @BindView(R.id.tv_custom_scroll_view6)
    TextView tvCustomScrollView6;
    @BindView(R.id.tv_custom_scroll_view7)
    TextView tvCustomScrollView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_custom_scroll_view1, R.id.tv_custom_scroll_view2, R.id.tv_custom_scroll_view3,
            R.id.tv_custom_scroll_view4, R.id.tv_custom_scroll_view5, R.id.tv_custom_scroll_view6,
            R.id.tv_custom_scroll_view7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_custom_scroll_view1:
                startActivity(new Intent(this, ZoomInScrollViewActivity1.class));
                break;
            case R.id.tv_custom_scroll_view2:
                startActivity(new Intent(this, ZoomInScrollViewActivity2.class));
                break;
            case R.id.tv_custom_scroll_view3:
                startActivity(new Intent(this, ZoomInScrollViewActivity3.class));
                break;
            case R.id.tv_custom_scroll_view4:
                startActivity(new Intent(this, ZoomInScrollViewActivity4.class));
                break;
            case R.id.tv_custom_scroll_view5:
                startActivity(new Intent(this, ZoomInScrollViewActivity5.class));
                break;
            case R.id.tv_custom_scroll_view6:
                startActivity(new Intent(this, ZoomInScrollViewActivity6.class));
                break;
            case R.id.tv_custom_scroll_view7:
                startActivity(new Intent(this,ZoomInScrollViewActivity7.class));
                break;
        }
    }
}
