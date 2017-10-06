package com.hsf.hsftest.image.scalelayout.scaleViewPager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.scalelayout.ScaleLayout;
import com.hsf.hsftest.image.scalelayout.TouchImageView;

import java.util.ArrayList;
import java.util.List;


public class ScaleViewPagerActivity extends AppCompatActivity {

    MultiViewPager mViewPager;
    TextView mTop;
    HorizontalScrollView mBottom;
    ScaleLayout mScaleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_scaleviewpager_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }


        mViewPager = (MultiViewPager) findViewById(R.id.scaleLayout_center);
        List<View> views = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            View v = getLayoutInflater().inflate(R.layout.viewpager_item_view, null, false);
            TouchImageView iv = (TouchImageView) v.findViewById(R.id.child_image);

            if(i == 0){
                iv.setImageResource(R.mipmap.image_1);
            }else if(i == 1){
                iv.setImageResource(R.mipmap.image_2);
            }else{
                iv.setImageResource(R.mipmap.image_3);
            }
            views.add(v);
        }

        MyPagerAdapter mAdapter = new MyPagerAdapter(views);
        mViewPager.setAdapter(mAdapter);

        mScaleLayout = (ScaleLayout) findViewById(R.id.scale_layout);
        mScaleLayout.setSuggestScaleEnable(true);
        mScaleLayout.addOnScaleChangedListener(new ScaleLayout.OnScaleChangedListener() {
            @Override
            public void onScaleChanged(float currentScale) {
                for (int i = 0; i < mViewPager.getChildCount(); i++) {
                    View view  = mViewPager.getChildAt(i);
                    view.setScaleX(currentScale);
                }
            }
        });

        mScaleLayout.setOnGetCanScaleListener(new ScaleLayout.OnGetCanScaleListener() {
            @Override
            public boolean onGetCanScale(boolean isScrollDown) {
                int currentItem = mViewPager.getCurrentItem();
                TouchImageView imageView = (TouchImageView)mViewPager.getChildAt(currentItem).findViewById(R.id.child_image);
                return !imageView.isZoomed();
            }
        });


        mTop = (TextView) findViewById(R.id.scaleLayout_top);
        mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("top view clicked!");
            }
        });

        mBottom = (HorizontalScrollView) findViewById(R.id.scaleLayout_bottom);
        ((ImageView)findViewById(R.id.bottom_image))
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showToast("bottom imageView clicked!");
            }
        });
    }


    public void showToast(String content){
        Toast.makeText(ScaleViewPagerActivity.this, content, Toast.LENGTH_SHORT).show();
    }
}
