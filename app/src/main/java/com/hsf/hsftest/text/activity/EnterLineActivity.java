package com.hsf.hsftest.text.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.text.view.AutoSplitTextView;
import com.hsf.hsftest.text.TextAndEditTextUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 hsf
 * @创建日期 2017/9/5 9:59
 * TextView 自动换行 整齐排版
 * http://blog.csdn.net/guankai1990/article/details/71647324
 */


public class EnterLineActivity extends AppCompatActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    AutoSplitTextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_line);
        ButterKnife.bind(this);

        tv1.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");
        tv1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                tv1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                final String newText = TextAndEditTextUtil.autoSplitText(tv1);
                if (!TextUtils.isEmpty(newText)) {
                    tv1.setText(newText);
                }
            }
        });
        tv2.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");
    }

}
