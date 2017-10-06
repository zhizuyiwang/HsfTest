package com.hsf.hsftest.image.viewfipper;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ViewFlipper;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/7 10:12
 * ViewFlipper的静态导入
 *  常用方法
    setInAnimation：View进入屏幕时使用动画；
    setOutAnimation：View退出屏幕时使用动画；
    showNext：显示ViewFlipper里的下一个View视图；
    showPrevious：显示ViewFlipper里的上一个View视图；
    setFlipInterval：View之间切换的时间间隔；
    setAutoStart：是否可以自动播放，true为自动播放，false为不自动播放；
    startFlipping：自动循环切换播放；
    stopFlipping：停止自动切换播放；
 */

public class ViewFlipperMethod1Activity extends AppCompatActivity {

    @BindView(R.id.vf_help)
    ViewFlipper vfHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_one);
        ButterKnife.bind(this);
        vfHelp.setAutoStart(false);
        vfHelp.startFlipping();
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(10000);
                vfHelp.stopFlipping();
            }
        }.start();
    }
}
