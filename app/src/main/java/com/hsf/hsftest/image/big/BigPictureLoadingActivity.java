package com.hsf.hsftest.image.big;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/9/1 10:28
 * 加载大图片
 * http://blog.csdn.net/decting/article/details/51589461
 */


public class BigPictureLoadingActivity extends AppCompatActivity {

    @BindView(R.id.btn_single_preview)
    Button btnSinglePreview;
    @BindView(R.id.btn_multi_preview)
    Button btnMultiPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_picture_loading);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_single_preview, R.id.btn_multi_preview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_single_preview:
                startActivity(new Intent(this,SinglePreviewActivity.class));
                break;
            case R.id.btn_multi_preview:
                startActivity(new Intent(this,MultiPreviewActivity.class));
                break;
        }
    }
}
