package com.hsf.hsftest.popup;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/11/6 16:52
 */

public class PopupWindowActivity extends AppCompatActivity {
    @BindView(R.id.tv_filter_popup)
    TextView tvSearchPopup;
    @BindView(R.id.tv_custom_popup)
    TextView tvCustomPopup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_popu);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tv_filter_popup, R.id.tv_custom_popup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_filter_popup:

                break;
            case R.id.tv_custom_popup:

                break;
        }
    }
}
