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

public class CustomRefreshRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_custom_recycler1)
    TextView tvCustomRecycler1;
    @BindView(R.id.tv_custom_recycler2)
    TextView tvCustomRecycler2;
    @BindView(R.id.tv_custom_recycler3)
    TextView tvCustomRecycler3;
    @BindView(R.id.tv_custom_recycler4)
    TextView tvCustomRecycler4;
    @BindView(R.id.tv_custom_recycler5)
    TextView tvCustomRecycler5;
    @BindView(R.id.tv_custom_recycler6)
    TextView tvCustomRecycler6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_refresh_recycler_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_custom_recycler1, R.id.tv_custom_recycler2, R.id.tv_custom_recycler3
            , R.id.tv_custom_recycler4, R.id.tv_custom_recycler5,R.id.tv_custom_recycler6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_custom_recycler1:
                startActivity(new Intent(this, CustomRecyclerViewActivity1.class));
                break;
            case R.id.tv_custom_recycler2:
                startActivity(new Intent(this, CustomRecyclerViewActivity2.class));
                break;
            case R.id.tv_custom_recycler3:
                startActivity(new Intent(this, CustomRecyclerViewActivity3.class));
                break;
            case R.id.tv_custom_recycler4:
                startActivity(new Intent(this, CustomRecyclerViewActivity4.class));
                break;
            case R.id.tv_custom_recycler5:
                startActivity(new Intent(this, CustomRecyclerViewActivity5.class));
                break;
            case R.id.tv_custom_recycler6:
                startActivity(new Intent(this,CustomRecyclerViewActivity6.class));
                break;
        }
    }


}
