package com.hsf.hsftest.dialog.nicedialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hsf.hsftest.R;

public class ConfirmNiceDialog extends BaseNiceDialog {
    private ViewConvertListener convertListener;
    private String title;
    private String content;

    public static ConfirmNiceDialog init() {
        return new ConfirmNiceDialog();
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
        holder.setText(R.id.title, title);
        holder.setText(R.id.message, content);
        if (convertListener != null) {
            convertListener.convertViewListener(holder, dialog);
        }
    }

    public ConfirmNiceDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public ConfirmNiceDialog setTitle(String  title) {
        this.title = title;
        return this;
    }

    public ConfirmNiceDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public ConfirmNiceDialog setConvertListener(ViewConvertListener convertListener) {
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
