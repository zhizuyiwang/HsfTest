package com.hsf.hsftest.image.drawable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawableHandleActivtiy extends AppCompatActivity {

    @BindView(R.id.tv_clip_drawable)
    TextView tvClipDrawable;
    @BindView(R.id.tv_rotate_Drawable)
    TextView tvRotateDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_handle_activtiy);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_clip_drawable, R.id.tv_rotate_Drawable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_clip_drawable:


                break;
            case R.id.tv_rotate_Drawable:
                startActivity(new Intent(this,WyMusicActivity.class));
                break;
        }
    }
}
