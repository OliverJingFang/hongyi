package com.cn.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

import com.cn.businese.AddLableActivity;
import com.cn.businese.MainActivity;
import com.cn.hongyi.R;
import com.cn.util.message.ToastUtil;

public class HongyiPushReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("HY", "____________");
        ToastUtil.showToast(context, "收到了通知");
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        Log.e("HY", "____________" + title);
        String title1 = bundle.getString(JPushInterface.EXTRA_TITLE);
        Log.e("HY", "____________" + title1);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        Log.e("HY", "____________" + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e("HY", "____________" + extras);
        String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
        Log.e("HY", "____________" + type);
        
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        }
        else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String msg = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            setMsgNotification(context, msg);
            
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        }
        else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        }
        else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MainActivity.class); // 自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        else {
            Log.d("HY", "Unhandled intent - ");
        }
    }
    
    private void setMsgNotification(Context mContext, String msg) {
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder;
        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContentTitle("测试标题")
                .setContentText(msg)
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL, mContext))
                // .setNumber(number)//显示数量
                .setTicker("测试通知来啦")
                // 通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())
                // 通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)
                // 设置该通知优先级
                // .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)
                // ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 //
                // requires VIBRATE permission
                .setSmallIcon(R.drawable.ic_launcher);
        
        Intent resultIntent = new Intent(mContext, AddLableActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(1, mBuilder.build());
    }
    
    public PendingIntent getDefalutIntent(int flags, Context mContext) {
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, new Intent(), flags);
        return pendingIntent;
    }
    
}
