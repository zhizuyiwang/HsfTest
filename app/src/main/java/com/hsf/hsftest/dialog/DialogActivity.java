package com.hsf.hsftest.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.dialog.nicedialog.NiceDialogActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.tv_nice_dialog)
    TextView tvNiceDialog;
    @BindView(R.id.tv_animation_dialog)
    TextView tvAnimationDialog;
    @BindView(R.id.tv_view_page_dialog)
    TextView tvViewPageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_nice_dialog, R.id.tv_animation_dialog,R.id.tv_view_page_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_nice_dialog:
                startActivity(new Intent(this, NiceDialogActivity.class));
                break;
            case R.id.tv_animation_dialog:

                break;
            case R.id.tv_view_page_dialog:
                //http://www.jianshu.com/p/584b27952dc3
                break;
        }
    }

    @OnClick()
    public void onViewClicked() {
    }
}
