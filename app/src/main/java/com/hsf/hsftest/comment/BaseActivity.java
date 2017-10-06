package com.hsf.hsftest.comment;

import android.app.Activity;
import android.os.Bundle;

import com.hsf.hsftest.util.utils.AppManager;


/**
 * @Description Activity的基类
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将Activity实例添加到AppManager的堆栈
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity实例从AppManager的堆栈中移除
        AppManager.getInstance().finishActivity(this);
    }
}
