package com.hsf.hsftest.dialog.nicedialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

public class NiceDialog extends BaseNiceDialog {
    private ViewConvertListener convertListener;

    public static NiceDialog init() {
        return new NiceDialog();
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
        Log.e("TAG","convertView先执行");
        if (convertListener != null) {
            convertListener.convertViewListener(holder, dialog);
        }

    }


    public NiceDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public NiceDialog setConvertListener(ViewConvertListener convertListener) {
        Log.e("TAG","setConvertListener先执行");
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = savedInstanceState.getParcelable("listener");
        }
    }
    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listener", convertListener);
    }
}
