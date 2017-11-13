package com.hsf.hsftest.image.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BannerActivity extends AppCompatActivity {

    @BindView(R.id.tv_banner1)
    TextView tvBanner1;
    @BindView(R.id.tv_banner2)
    TextView tvBanner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_banner1, R.id.tv_banner2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_banner1:
                startActivity(new Intent(this,BannerActivity1.class));
                break;
            case R.id.tv_banner2:
                break;
        }
    }
}
