package com.hsf.hsftest.activity.customview.bezier;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.WaveViewByBezier;

public class CustomBezierWaterRippleActivity extends AppCompatActivity {
    WaveViewByBezier waveViewByBezier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bezier_water_ripple);
        waveViewByBezier = (WaveViewByBezier) findViewById(R.id.wave_bezier);
        waveViewByBezier.startAnimation();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();
        waveViewByBezier.pauseAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        waveViewByBezier.resumeAnimation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        waveViewByBezier.stopAnimation();
    }
}
