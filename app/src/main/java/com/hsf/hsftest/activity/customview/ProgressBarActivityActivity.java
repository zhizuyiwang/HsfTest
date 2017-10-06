package com.hsf.hsftest.activity.customview;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.CircleProgressBarView;
import com.hsf.hsftest.weight.HorizontalProgressBar;

public class ProgressBarActivityActivity extends AppCompatActivity {


    CircleProgressBarView circleProgressBarView;
    HorizontalProgressBar horizontalProgressBar;
    TextView textView;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_horizontal);

        circleProgressBarView = (CircleProgressBarView) findViewById(R.id.circle_progress_view);

        horizontalProgressBar = (HorizontalProgressBar) findViewById(R.id.horizontal_progress_view);

        textView = (TextView) findViewById(R.id.progress_tv);
        button = (Button) findViewById(R.id.startAnimationBtn);

        circleProgressBarView.setProgressWithAnimation(60);
        circleProgressBarView.setProgressListener(new CircleProgressBarView.ProgressListener() {
            @Override
            public void currentProgressListener(float currentProgress) {
                textView.setText("当前进度：" + currentProgress);
            }
        });
        circleProgressBarView.startProgressAnimation();

        horizontalProgressBar.setProgressWithAnimation(60).setProgressListener(new HorizontalProgressBar.ProgressListener() {
            @Override
            public void currentProgressListener(float currentProgress) {
            }
        });
        horizontalProgressBar.startProgressAnimation();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontalProgressBar.setProgressWithAnimation(100);
                circleProgressBarView.setProgressWithAnimation(60).startProgressAnimation();
                circleProgressBarView.setProgressListener(new CircleProgressBarView.ProgressListener() {
                    @Override
                    public void currentProgressListener(float currentProgress) {
                        textView.setText("当前进度：" + currentProgress);
                    }
                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        circleProgressBarView.resumeProgressAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();
        circleProgressBarView.pauseProgressAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        circleProgressBarView.stopProgressAnimation();
    }
}
