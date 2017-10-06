package com.hsf.hsftest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.activity.customview.CustomViewActivity;
import com.hsf.hsftest.activity.shade.ShadeLayoutActivity;
import com.hsf.hsftest.activity.view.MotionEventActivity;
import com.hsf.hsftest.design.MaterialDesignActivity;
import com.hsf.hsftest.dialog.DialogActivity;
import com.hsf.hsftest.image.ImageHandleActivity;
import com.hsf.hsftest.text.TextViewActivity;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.TokenResult;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 hsf
 * @创建日期 2017/8/23 11:47
 */

public class MainActivity extends AppCompatActivity implements HuaweiApiClient.ConnectionCallbacks,HuaweiApiClient.OnConnectionFailedListener{
    @BindView(R.id.tv_run_text)
    TextView tvRunText;
    @BindView(R.id.tv_soft_input_text)
    TextView tvSoftInputText;
    @BindView(R.id.tv_permission_location)
    TextView tvPermissionLocation;
    @BindView(R.id.tv_motion_event)
    TextView tvMotionEvent;
    @BindView(R.id.tv_shape_layout)
    TextView tvShapeLayout;
    @BindView(R.id.tv_custom_view)
    TextView tvCustomView;
    @BindView(R.id.tv_material_design)
    TextView tvMaterialDesign;
    @BindView(R.id.tv_rx_java)
    TextView tvRxJava;
    @BindView(R.id.tv_select_wheel)
    TextView tvSelectWheel;
    @BindView(R.id.tv_image_handle)
    TextView tvImageHandle;
    @BindView(R.id.tv_design_pattern)
    TextView tvDesignPattern;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.tv_con)
    TextView tvCon;
    @BindView(R.id.tv_integration)
    TextView tvIntegration;
    @BindView(R.id.tv_media)
    TextView tvMedia;
    @BindView(R.id.tv_popup)
    TextView tvPopup;
    private static final String TAG = "MainActivity";
    //华为移动服务Client
    private HuaweiApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        client = new HuaweiApiClient.Builder(this)
                .addApi(HuaweiPush.PUSH_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        //业务可以根据自己业务的形态来确定client的连接和断开的时机，但是确保connect和disconnect必须成对出现
        client.connect();

    }

    @OnClick({R.id.tv_run_text, R.id.tv_soft_input_text, R.id.tv_permission_location, R.id.tv_motion_event,
            R.id.tv_shape_layout, R.id.tv_custom_view, R.id.tv_material_design,
            R.id.tv_rx_java, R.id.tv_select_wheel, R.id.tv_image_handle, R.id.tv_design_pattern,
            R.id.tv_dialog, R.id.tv_integration, R.id.tv_media})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_run_text:
                startActivity(new Intent(this, TextViewActivity.class));
                break;
            case R.id.tv_custom_view:
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
            case R.id.tv_soft_input_text:
                break;
            case R.id.tv_permission_location:
                break;
            case R.id.tv_motion_event:
                startActivity(new Intent(this, MotionEventActivity.class));
                break;
            case R.id.tv_shape_layout:
                startActivity(new Intent(this, ShadeLayoutActivity.class));
                break;
            case R.id.tv_material_design:
                startActivity(new Intent(this, MaterialDesignActivity.class));
                break;
            case R.id.tv_rx_java:
                break;
            case R.id.tv_select_wheel:
                break;
            case R.id.tv_image_handle:
                startActivity(new Intent(this, ImageHandleActivity.class));
                break;
            case R.id.tv_design_pattern:
                break;
            case R.id.tv_dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.tv_popup:

                break;
            case R.id.tv_integration:

                break;
            case R.id.tv_media:
                startActivity(new Intent(this, MediaPlayerActivity.class));
                break;
        }
    }

    /**
     * 异步获取Token
     */
    private void getTokenAsyn() {
        if(!client.isConnected()) {
            Log.i(TAG, "获取token失败，原因：HuaweiApiClient未连接");
            client.connect();
            return;
        }
        Log.i(TAG, "异步接口获取push token");
        PendingResult<TokenResult> tokenResult = HuaweiPush.HuaweiPushApi.getToken(client);
        tokenResult.setResultCallback(new ResultCallback<TokenResult>() {

            @Override
            public void onResult(TokenResult result) {
                //这边的结果只表明接口调用成功，是否能收到响应结果只在广播中接收
            }
        });
    }

    @Override
    public void onConnected() {
        //华为移动服务client连接成功，在这边处理业务自己的事件
        Log.i("TAG", "HuaWeiApiClient 连接成功");
        getTokenAsyn();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onConnectionSuspended(int i) {
        //HuaWeiApiClient异常断开连接, if 括号里的条件可以根据需要修改
        if (!this.isDestroyed() && !this.isFinishing()) {
            client.connect();
        }
        Log.i("TAG", "HuaWeiApiClient 连接断开");
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("TAG", "HuaWeiApiClient连接失败，错误码：" + connectionResult.getErrorCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(client != null){
            client.disconnect();
        }
    }
}
