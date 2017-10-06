package com.hsf.hsftest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaPlayerActivity extends AppCompatActivity {

    @BindView(R.id.tv_audio)
    TextView tvAudio;
    @BindView(R.id.tv_video)
    TextView tvVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_audio, R.id.tv_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_audio:
                break;
            case R.id.tv_video:
                break;
        }
    }
}
