package com.hsf.hsftest.activity.customview.loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.hsftest.R;
import com.hsf.hsftest.util.utils.DialogUtil;

public class DialogLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_loading);
        DialogUtil.showDialogLoading(this,"正在加载");
    }
}
