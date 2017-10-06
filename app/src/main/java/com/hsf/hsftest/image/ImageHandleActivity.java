package com.hsf.hsftest.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.big.BigPictureLoadingActivity;
import com.hsf.hsftest.image.circleimage.CustomCircleImageActivity;
import com.hsf.hsftest.image.drawable.DrawableHandleActivtiy;
import com.hsf.hsftest.image.imagezoom.ShowImageActivity;
import com.hsf.hsftest.image.scalelayout.scaleViewPager.ScaleViewPagerActivity;
import com.hsf.hsftest.image.viewfipper.ViewFlipperActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/9/5 11:35
 * 图片操作
 */

public class ImageHandleActivity extends AppCompatActivity {

    @BindView(R.id.tv_take_photo)
    TextView tvTakePhoto;
    @BindView(R.id.tv_image_pull_to_zoom)
    TextView tvImagePullToZoom;
    @BindView(R.id.tv_select_image_loading)
    TextView tvSelectImageLoading;
    @BindView(R.id.tv_circle_image)
    TextView tvCircleImage;
    @BindView(R.id.tv_big_picture)
    TextView tvBigPicture;
    @BindView(R.id.tv_view_flipper)
    TextView tvViewFlipper;
    @BindView(R.id.tv_view_preview)
    TextView tvViewPreview;
    @BindView(R.id.tv_scale_layout)
    TextView tvScaleLayout;
    @BindView(R.id.tv_drawable)
    TextView tvDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_handle);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_take_photo, R.id.tv_image_pull_to_zoom, R.id.tv_select_image_loading, R.id.tv_circle_image,
            R.id.tv_big_picture, R.id.tv_view_flipper, R.id.tv_view_preview, R.id.tv_scale_layout,R.id.tv_drawable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_take_photo:
                break;
            case R.id.tv_image_pull_to_zoom:
                break;
            case R.id.tv_select_image_loading:
                break;
            case R.id.tv_circle_image:
                startActivity(new Intent(this, CustomCircleImageActivity.class));
                break;
            case R.id.tv_big_picture:
                startActivity(new Intent(this, BigPictureLoadingActivity.class));
                break;
            case R.id.tv_view_flipper:
                startActivity(new Intent(this, ViewFlipperActivity.class));
                break;
            case R.id.tv_scale_layout:
                //startActivity(new Intent(this, ScaleActivity.class));
                startActivity(new Intent(this, ScaleViewPagerActivity.class));
                break;
            case R.id.tv_view_preview:
                //startActivity(new Intent(this, ZoomImageActivity.class));
                //startActivity(new Intent(this, SpacePageActivity.class));
                startActivity(new Intent(this, ShowImageActivity.class));
                //http://blog.csdn.net/limb99/article/details/18503723#comments
                break;
            case R.id.tv_drawable:
                startActivity(new Intent(this, DrawableHandleActivtiy.class));
                break;
        }
    }

    @OnClick()
    public void onViewClicked() {
    }
}
