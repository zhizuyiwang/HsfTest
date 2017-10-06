package com.hsf.hsftest.image.viewfipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewFlipperActivity extends AppCompatActivity {

    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.btn_method_one)
    Button btnMethodOne;
    @BindView(R.id.btn_method_two)
    Button btnMethodTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.titleView, R.id.btn_method_one, R.id.btn_method_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titleView:
                break;
            case R.id.btn_method_one:
                startActivity(new Intent(this,ViewFlipperMethod1Activity.class));
                break;
            case R.id.btn_method_two:
                startActivity(new Intent(this,ViewFlipperMethod2Activity.class));
                break;
        }
    }
}
