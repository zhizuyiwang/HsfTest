package com.hsf.hsftest.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hsf.hsftest.R;
import com.hsf.hsftest.comment.MyApplication;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public class MeiZuPushReceiver extends MzPushMessageReceiver {
    private static final String TAG = "MeiZuPushReceiver";

    //调用PushManager.register(context）方法后，会在此回调注册状态
    //应用在接受返回的pushid
    @Override
    public void onRegister(Context context, String pushId) {
        Log.e(TAG, "onRegister pushID " + pushId);
    }

    @Override
    public void onMessage(Context context, Intent intent) {
        super.onMessage(context, intent);

    }

    //接收服务器推送的透传消息
    @Override
    public void onMessage(Context context, String message) {
        super.onMessage(context, message);
        Log.e(TAG, "message " + message);
    }

    @Override
    public void onMessage(Context context, String message, String platformExtra) {
        super.onMessage(context, message, platformExtra);
    }

    //调用PushManager.unRegister(context）方法后，会在此回调反注册状态
    @Override
    public void onUnRegister(Context context, boolean success) {
        Log.e(TAG, "onUnRegister " + success);
    }
    //设置通知栏小图标
    @Override
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        //重要,详情参考应用小图标自定设置
        Log.e(TAG, "设置图标");
        //设置通知栏弹出的小图标
        pushNotificationBuilder.setmStatusbarIcon(R.drawable.mz_push_notification_small_icon);
    }

    //检查通知栏和透传消息开关状态回调
    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {

    }

    //调用新版订阅PushManager.register(context,appId,appKey)回调
    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        Log.e(TAG, "新版pushID " + registerStatus.getPushId());
        PushManager.switchPush(MyApplication.mContext,"111404","4f2117bd333749648b3b0aa071340d08",registerStatus.getPushId(),true);
    }
    //新版反订阅回调
    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {

    }

    //标签回调
    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {

    }
    //别名回调
    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {

    }

    @Override
    public void onNotificationArrived(Context context, String title, String content, String selfDefineContentString) {
        //通知栏消息到达回调，flyme6基于android6.0以上不再回调
        Log.e(TAG, "通知来了");
    }

    @Override
    public void onNotificationClicked(Context context, String title, String content, String selfDefineContentString) {
        //通知栏消息点击回调
        Log.e(TAG, "通知栏标题===== " + title+"==通知栏消息== " + content);
    }

    @Override
    public void onNotificationDeleted(Context context, String title, String content, String selfDefineContentString) {
        //通知栏消息删除回调；flyme6基于android6.0以上不再回调
    }
}
