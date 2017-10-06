package com.hsf.hsftest.text.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.TitleView;
import com.hsf.hsftest.text.view.ExpandableTextView;
import com.hsf.hsftest.text.view.MoreLineTextView;
import com.hsf.hsftest.util.utils.Constant;
import com.hsf.hsftest.util.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandableTextViewActivity extends AppCompatActivity {

    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.tv_more_line_short)
    MoreLineTextView tvMoreLineShort;
    @BindView(R.id.tv_expandable_short)
    ExpandableTextView tvExpandableShort;
    @BindView(R.id.tv_more_line_long)
    MoreLineTextView tvMoreLineLong;
    @BindView(R.id.tv_expandable_long)
    ExpandableTextView tvExpandableLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_text_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        tvMoreLineShort.setText(Constant.content1);
        tvExpandableShort.setText(Constant.content2);
        tvMoreLineLong.setText(Constant.content3);
        tvExpandableLong.setText(Constant.content4);
    }

    private void initView() {
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImageVisibility(View.GONE);
    }
}
