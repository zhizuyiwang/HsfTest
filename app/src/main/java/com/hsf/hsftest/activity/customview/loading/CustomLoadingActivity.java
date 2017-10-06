package com.hsf.hsftest.activity.customview.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomLoadingActivity extends AppCompatActivity {

    @BindView(R.id.tv_custom_loading1)
    TextView tvCustomLoading1;
    @BindView(R.id.tv_custom_loading2)
    TextView tvCustomLoading2;
    @BindView(R.id.tv_custom_loading3)
    TextView tvCustomLoading3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loading);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_custom_loading1, R.id.tv_custom_loading2,R.id.tv_custom_loading3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_custom_loading1:
                startActivity(new Intent(this, BounceLoadingActivity.class));
                break;
            case R.id.tv_custom_loading2:
                startActivity(new Intent(this, GradeProgressActivity.class));
                break;
            case R.id.tv_custom_loading3:
                startActivity(new Intent(this,DialogLoadingActivity.class));
                break;

        }
    }

    @OnClick()
    public void onViewClicked() {
    }
}
