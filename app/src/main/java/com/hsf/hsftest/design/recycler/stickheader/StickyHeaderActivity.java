package com.hsf.hsftest.design.recycler.stickheader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StickyHeaderActivity extends AppCompatActivity {

    @BindView(R.id.tv_sticky1)
    TextView tvSticky1;
    @BindView(R.id.tv_sticky2)
    TextView tvSticky2;
    @BindView(R.id.tv_sticky3)
    TextView tvSticky3;
    @BindView(R.id.tv_sticky4)
    TextView tvSticky4;
    @BindView(R.id.tv_sticky5)
    TextView tvSticky5;
    @BindView(R.id.tv_sticky6)
    TextView tvSticky6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_sticky1, R.id.tv_sticky2, R.id.tv_sticky3, R.id.tv_sticky4, R.id.tv_sticky5,R.id.tv_sticky6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sticky1:
                startActivity(new Intent(this, StickyHeaderActivity1.class));
                break;
            case R.id.tv_sticky2:
                startActivity(new Intent(this, StickyHeaderActivity2.class));
                break;
            case R.id.tv_sticky3:
                startActivity(new Intent(this, StickyHeaderActivity3.class));
                break;
            case R.id.tv_sticky4:
                startActivity(new Intent(this, StickyHeaderActivity4.class));
                break;
            case R.id.tv_sticky5:
                startActivity(new Intent(this, StickyHeaderActivity5.class));
                break;
            case R.id.tv_sticky6:
                startActivity(new Intent(this,StickyHeaderActivity6.class));
                break;
        }
    }
}
