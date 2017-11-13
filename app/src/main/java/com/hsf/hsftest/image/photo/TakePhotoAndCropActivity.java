package com.hsf.hsftest.image.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakePhotoAndCropActivity extends AppCompatActivity {

    @BindView(R.id.tv_take_photo1)
    TextView tvTakePhoto1;
    @BindView(R.id.tv_take_photo2)
    TextView tvTakePhoto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_and_crop);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_take_photo1, R.id.tv_take_photo2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_take_photo1:
                startActivity(new Intent(this,CommonTakPhotoActivity.class));
                break;
            case R.id.tv_take_photo2:
                startActivity(new Intent(this,CropPhotoActivity.class));
                break;
        }
    }
}
