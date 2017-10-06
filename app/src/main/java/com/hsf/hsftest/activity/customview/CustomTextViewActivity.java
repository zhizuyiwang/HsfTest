package com.hsf.hsftest.activity.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.FadeInTextView;
import com.hsf.hsftest.weight.LoadingButton;

public class CustomTextViewActivity extends AppCompatActivity {
    private FadeInTextView fadeInTextView;
    private LoadingButton loadingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_view);
        fadeInTextView = (FadeInTextView) findViewById(R.id.fade_in_tv);
        loadingButton = (LoadingButton) findViewById(R.id.loading_btn);
        fadeInTextView.setTextString("大家好，我的名字叫黄善飞，可以称呼我扛把子。我是一个喜欢打架的小男孩，曾经我一个人打倒了4个大汉！")
                .setTextAnimationListener(new FadeInTextView.TextAnimationListener() {
                    @Override
                    public void animationFinish() {
                        loadingButton.stopLoading();

                    }
                });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingButton.startLoading();
                fadeInTextView.startFadeInAnimation();
            }
        });
    }
}
