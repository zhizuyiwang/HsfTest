package com.hsf.hsftest.comment;

import android.app.Application;
import android.content.Context;

/**
 * Created by Thinkpadx240 on 2017/9/5.
 */

public class MyApplication extends Application{
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
