package com.hsf.hsftest.activity.customview.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/1 16:50
 * https://www.2cto.com/kf/201604/497130.html
 * http://www.jb51.net/article/108255.htm
 */


public class BezierLineActivity extends AppCompatActivity {

    @BindView(R.id.tv_two_bezier)
    TextView tvTwoBezier;
    @BindView(R.id.tv_three_bezier)
    TextView tvThreeBezier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_line);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_two_bezier, R.id.tv_three_bezier})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_two_bezier:
                startActivity(new Intent(this,TwoBezierLineActivity.class));
                break;
            case R.id.tv_three_bezier:
                startActivity(new Intent(this,ThreeBezierLineActivity.class));
                break;
        }
    }
}
