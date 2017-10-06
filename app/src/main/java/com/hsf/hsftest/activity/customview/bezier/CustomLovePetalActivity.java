package com.hsf.hsftest.activity.customview.bezier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.HeartView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/8/28 10:22
 */


public class CustomLovePetalActivity extends AppCompatActivity {
    @BindView(R.id.heart_view)
    HeartView heartview;
    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_petal_love);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.heart_view, R.id.rl_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.heart_view:
                Log.e("TAG","kkkkk");
                heartview.reDraw();
                break;
            case R.id.rl_container:

                break;
        }
    }
}
