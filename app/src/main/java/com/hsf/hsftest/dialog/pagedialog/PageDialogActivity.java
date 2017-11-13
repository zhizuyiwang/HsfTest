package com.hsf.hsftest.dialog.pagedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hsf.hsftest.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PageDialogActivity extends AppCompatActivity {
    PagerDialog pagerDialog;
    private String[] imageList = new String[]{"http://otfl590no.bkt.clouddn.com/1.jpg?attname=",
            "http://otfl590no.bkt.clouddn.com/2.jpg?attname=",
            "http://otfl590no.bkt.clouddn.com/3.jpg?attname=",
            "http://otfl590no.bkt.clouddn.com/4.jpg?attname="};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog1);
        pagerDialog = new PagerDialog(this, imageList);
        pagerDialog.show();
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dismiss(MessageEvent messageEvent) {
        pagerDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
