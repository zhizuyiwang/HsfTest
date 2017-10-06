package com.hsf.hsftest.receiver;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hsf.hsftest.MainActivity;
import com.huawei.hms.support.api.push.PushReceiver;

/**
 * 应用需要创建一个子类继承com.huawei.hms.support.api.push.PushReceiver，
 * 实现onToken，onPushState ，onPushMsg，onEvent，这几个抽象方法，用来接收token返回，push连接状态，透传消息和通知栏点击事件处理。
 * onToken 调用getToken方法后，获取服务端返回的token结果，返回token以及belongId
 * onPushState 调用getPushState方法后，获取push连接状态的查询结果
 * onPushMsg 推送消息下来时会自动回调onPushMsg方法实现应用透传消息处理。本接口必须被实现。 在开发者网站上发送push消息分为通知和透传消息
 *           通知为直接在通知栏收到通知，通过点击可以打开网页，应用 或者富媒体，不会收到onPushMsg消息
 *           透传消息不会展示在通知栏，应用会收到onPushMsg
 * onEvent 该方法会在设置标签、点击打开通知栏消息、点击通知栏上的按钮之后被调用。由业务决定是否调用该函数。
 */
public class HuaWeiPushReceiver extends PushReceiver {
    public static final String TAG = "HuaweiPushRevicer";

    /**
     * 连接上华为服务时会调用,可以获取token值
     * @param context
     * @param token
     * @param extras
     */
    @Override
    public void onToken(Context context, String token, Bundle extras) {
        String belongId = extras.getString("belongId");
        Log.i(TAG, "belongId为:" + belongId);
        Log.i(TAG, "Token为:" + token);

        System.out.println("Token为:" + token);
    }

    /**
     * 透传消息的回调方法
     * @param context
     * @param msg
     * @param bundle
     * @return
     */
    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        context.startActivity(new Intent(context, MainActivity.class));
        try {
            //解析消息内容，然后做相应的处理
            String content = new String(msg, "UTF-8");
            Log.i(TAG, "收到PUSH透传消息,消息内容为:" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 自定义的消息的回调方法
     * @param context
     * @param event
     * @param extras
     */
    public void onEvent(Context context, Event event, Bundle extras) {

        Intent intent = new Intent();
        intent.setAction("ACTION_UPDATEUI");

        intent.putExtra("type", 3);
        intent.putExtra("pushMsg", "收到通知栏消息点击事件");
        context.sendBroadcast(intent);
        //NOTIFICATION_OPENED,通知栏中的通知被点击打开
        //NOTIFICATION_CLICK_BTN,通知栏中通知上的按钮被点击
        if (Event.NOTIFICATION_OPENED.equals(event) ||
                Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
            String content = "收到通知附加消息:" + extras.getString(BOUND_KEY.pushMsgKey);
            Log.i(TAG, "消息点击事件得到通知附加消息:" + content);
        }
    }

    /**
     * 连接状态的回调方法
     * @param context
     * @param pushState
     */
    @Override
    public void onPushState(Context context, boolean pushState) {
        Log.i("TAG", "Push连接状态为:" + pushState);
    }

}
