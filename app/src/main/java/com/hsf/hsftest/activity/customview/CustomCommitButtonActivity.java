package com.hsf.hsftest.activity.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.AnimationButton;

public class CustomCommitButtonActivity extends AppCompatActivity {
    private AnimationButton animationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_commit);
        animationButton = (AnimationButton) findViewById(R.id.animation_btn);
        animationButton.setAnimationButtonListener(new AnimationButton.AnimationButtonListener() {
            @Override
            public void onClickListener() {
                animationButton.start();
            }

            @Override
            public void animationFinish() {
                Toast.makeText(CustomCommitButtonActivity.this,"动画执行完毕",Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }
}
