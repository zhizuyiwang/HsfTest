package com.hsf.hsftest.text.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.hsf.hsftest.text.TextAndEditTextUtil;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/5 10:06
 * 封装一个自定义的TextView，直接包含自动排版换行的功能
 */


public class AutoSplitTextView extends android.support.v7.widget.AppCompatTextView {
    private boolean mEnabled = true;
    public AutoSplitTextView(Context context) {
        super(context);
    }

    public AutoSplitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSplitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAutoSplitEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
                && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY
                && getWidth() > 0
                && getHeight() > 0
                && mEnabled) {
            String newText = TextAndEditTextUtil.autoSplitText(this);
            if (!TextUtils.isEmpty(newText)) {
                setText(newText);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
