package com.hsf.hsftest.text;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.text.activity.AutoCompleteTextViewActivity;
import com.hsf.hsftest.text.activity.AutoEnterLineActivity;
import com.hsf.hsftest.text.activity.EnterLineActivity;
import com.hsf.hsftest.text.activity.ExpandableTextViewActivity;
import com.hsf.hsftest.text.activity.NumberRollingTextViewActivity;
import com.hsf.hsftest.text.activity.SpaceEditTextActivity;
import com.hsf.hsftest.text.activity.StateButtonActivity;
import com.hsf.hsftest.text.activity.SuperTextViewActivity;
import com.hsf.hsftest.text.activity.TextLabelActivity;
import com.hsf.hsftest.text.activity.TextRunningActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/8/23 11:48
 * 所有TextView有关的用法
 */

public class TextViewActivity extends AppCompatActivity {
    @BindView(R.id.tv_run_text)
    TextView tvRunText;
    @BindView(R.id.tv_aotu_text)
    TextView tvAotuText;
    @BindView(R.id.tv_super_text)
    TextView tvSuperText;
    @BindView(R.id.tv_enter_line)
    TextView tvEnterLine;
    @BindView(R.id.tv_space_et)
    TextView tvSpaceEt;
    @BindView(R.id.tv_number_view)
    TextView tvNumberView;
    @BindView(R.id.tv_more_line_expand)
    TextView tvMoreLineExpand;
    @BindView(R.id.tv_state_button)
    TextView tvStateButton;
    @BindView(R.id.tv_auto_enter_line)
    TextView tvAutoEnterLine;
    @BindView(R.id.tv_label)
    TextView tvLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_run_text, R.id.tv_aotu_text, R.id.tv_super_text, R.id.tv_enter_line, R.id.tv_space_et
            , R.id.tv_number_view, R.id.tv_more_line_expand, R.id.tv_state_button, R.id.tv_auto_enter_line,R.id.tv_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_run_text:
                startActivity(new Intent(this, TextRunningActivity.class));
                break;
            case R.id.tv_aotu_text:
                startActivity(new Intent(this, AutoCompleteTextViewActivity.class));
                break;
            case R.id.tv_super_text:
                startActivity(new Intent(this, SuperTextViewActivity.class));
                break;
            case R.id.tv_enter_line:
                startActivity(new Intent(this, EnterLineActivity.class));
                break;
            case R.id.tv_auto_enter_line:
                startActivity(new Intent(this, AutoEnterLineActivity.class));
                break;
            case R.id.tv_space_et:
                startActivity(new Intent(this, SpaceEditTextActivity.class));
                break;
            case R.id.tv_number_view:
                startActivity(new Intent(this, NumberRollingTextViewActivity.class));
                break;
            case R.id.tv_more_line_expand:
                startActivity(new Intent(this, ExpandableTextViewActivity.class));
                break;
            case R.id.tv_state_button:
                startActivity(new Intent(this, StateButtonActivity.class));
                break;
            case R.id.tv_label:
                startActivity(new Intent(this, TextLabelActivity.class));
                break;
        }
    }
}
