package com.hsf.hsftest.text.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hsf.hsftest.R;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;
/**
 *@作者 hsf
 *
 *@创建日期 2017/8/23 11:48
 * 跑马灯的用法
 */

public class TextRunningActivity extends AppCompatActivity {
    private MarqueeView marqueeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_running);
        marqueeView = (MarqueeView)findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是孙福生。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：孙福生微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：孙福生");
        marqueeView.startWithList(info);
        // 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

        String notice = "心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！";
        // 在代码里设置自己的动画
        marqueeView.startWithText(notice, R.anim.anim_left_in, R.anim.anim_right_out);
        //设置事件监听
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), String.valueOf(marqueeView.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        /**所谓跑马灯效果就是当文字超过控件所能容纳的空间时，在控件内滚动的效果：
         *显示跑马灯效果的前提条件就是你的文本内容要比显示文本的外部组件长，即外部组件无法完整的显示内部的文本内容。
         * 1、普通方式：
         *      //单行显示文字
         *  android:singleLine="true"
         *      //设置为跑马灯显示
         *  android:ellipsize="marquee"
         *      //可以通过toucth来获得focus
         *  android:focusableInTouchMode="true"
         *       //获取焦点
         *  android:focusable="true"
         *      //设置重复的次数
         *  android:marqueeRepeatLimit="marquee_forever"
         *
         *
         * 2、重写TextView，取消焦点限制：
         * 但是当需要文字一直滚动时，有可能会出现别的控件获得焦点的情况。
         * 这样就需要实现自定义控件，然后达到TextView一直处于滚动状态的效果。
         *
         * 3、自定义控件循环走跑马灯：
         *      大部分走马灯文字会在手机屏幕的右侧开始，这需要自定义控件来实现了
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * TextView的android:clickable="true" 属性分析，为TextView定义了点击事件，当被点击时，会改变TextView的isClickable的值
             public void setOnClickListener(OnClickListener l) {
                if (!isClickable()) {
                setClickable(true);
             }
                getListenerInfo().mOnClickListener = l;
             }
         *
         *
        */
    }
    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }
    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }
}
