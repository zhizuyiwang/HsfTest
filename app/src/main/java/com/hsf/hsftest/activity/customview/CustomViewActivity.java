package com.hsf.hsftest.activity.customview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.activity.customview.bezier.BezierLineActivity;
import com.hsf.hsftest.activity.customview.bezier.CustomBezierWaterRippleActivity;
import com.hsf.hsftest.activity.customview.bezier.CustomLoveLineActivity;
import com.hsf.hsftest.activity.customview.bezier.CustomLovePetalActivity;
import com.hsf.hsftest.activity.customview.bezier.CustomSinWaterRippleActivity;
import com.hsf.hsftest.activity.customview.card.CustomDrawerCardActivity;
import com.hsf.hsftest.activity.customview.loading.CustomLoadingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_custom_commit_btn)
    TextView tvCustomCommitBtn;
    @BindView(R.id.tv_custom_text)
    TextView TvCustomText;
    @BindView(R.id.tv_custom_love)
    TextView TvCustomLove;
    @BindView(R.id.tv_custom_love_password)
    TextView tvCustomLovePassword;
    @BindView(R.id.tv_custom_horizontal_progress)
    TextView tvCustomHorizontalProgress;
    @BindView(R.id.tv_custom_sin_water_ripple)
    TextView tvCustomWaterRipple;
    @BindView(R.id.tv_custom_bezier_water_ripple)
    TextView tvBezierCustomWaterRipple;
    @BindView(R.id.tv_custom_love_line)
    TextView tvCustomLoveLine;
    @BindView(R.id.tv_custom_snow)
    TextView tvCustomSnow;
    @BindView(R.id.tv_bezier_line)
    TextView tvBezierLine;
    @BindView(R.id.tv_custom_card)
    TextView tvCustomCard;
    @BindView(R.id.tv_custom_loading)
    TextView tvCustomLoading;
    @BindView(R.id.tv_custom_qq_message)
    TextView tvCustomQqMessage;
    @BindView(R.id.tv_custom_wy_music)
    TextView tvCustomWyMusic;
    @BindView(R.id.tv_custom_horizontal_progress2)
    TextView tvCustomHorizontalProgress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costom_view);
        ButterKnife.bind(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("myscheme://com.hsf.hsftest/push_detail?message=what"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String intentUri = intent.toUri(Intent.URI_INTENT_SCHEME);
        Log.i("HuaweiPushRevicer", "action是:" + intentUri);
        Intent intent1 = getIntent();
        int ww = intent1.getIntExtra("kkk", 0);
        Log.i("HuaweiPushRevicer", "kkk:" + ww);
    }

    @OnClick({R.id.tv_custom_commit_btn, R.id.tv_custom_text, R.id.tv_custom_love, R.id.tv_custom_love_password,
            R.id.tv_custom_horizontal_progress, R.id.tv_custom_sin_water_ripple, R.id.tv_custom_bezier_water_ripple,
            R.id.tv_custom_love_line, R.id.tv_custom_snow, R.id.tv_bezier_line, R.id.tv_custom_card, R.id.tv_custom_loading,
            R.id.tv_custom_qq_message, R.id.tv_custom_wy_music,R.id.tv_custom_horizontal_progress2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_custom_commit_btn:
                startActivity(new Intent(this, CustomCommitButtonActivity.class));
                break;
            case R.id.tv_custom_text:
                startActivity(new Intent(this, CustomTextViewActivity.class));
                break;
            case R.id.tv_custom_love:
                startActivity(new Intent(this, CustomLovePetalActivity.class));
                break;
            case R.id.tv_custom_love_password:
                startActivity(new Intent(this, CustomPassWordActivity.class));
                break;
            case R.id.tv_custom_horizontal_progress:
                startActivity(new Intent(this, ProgressBarActivityActivity.class));
                break;
            case R.id.tv_custom_horizontal_progress2:
                startActivity(new Intent(this,ProgressBarActivityActivity2.class));
                break;
            case R.id.tv_custom_sin_water_ripple:
                startActivity(new Intent(this, CustomSinWaterRippleActivity.class));
                break;
            case R.id.tv_custom_bezier_water_ripple:
                startActivity(new Intent(this, CustomBezierWaterRippleActivity.class));
                break;
            case R.id.tv_custom_love_line:
                startActivity(new Intent(this, CustomLoveLineActivity.class));
                break;
            case R.id.tv_custom_snow:
                startActivity(new Intent(this, CustomSnowActivity.class));
                break;
            case R.id.tv_bezier_line:
                startActivity(new Intent(this, BezierLineActivity.class));
                break;
            case R.id.tv_custom_card:
                startActivity(new Intent(this, CustomDrawerCardActivity.class));
                break;
            case R.id.tv_custom_loading:
                startActivity(new Intent(this, CustomLoadingActivity.class));
                break;
            case R.id.tv_custom_qq_message:
                break;
            case R.id.tv_custom_wy_music:

                break;

        }
    }

    @OnClick()
    public void onViewClicked() {
    }
}
