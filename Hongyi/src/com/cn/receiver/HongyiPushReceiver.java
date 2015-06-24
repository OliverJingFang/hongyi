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
        ToastUtil.showToast(context, "�յ���֪ͨ");
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
            
            // �Զ�����Ϣ����չʾ��֪ͨ������ȫҪ������д����ȥ����
        }
        else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("�յ���֪ͨ");
            // �����������Щͳ�ƣ�������Щ��������
        }
        else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("�û��������֪ͨ");
            // ����������Լ�д����ȥ�����û���������Ϊ
            Intent i = new Intent(context, MainActivity.class); // �Զ���򿪵Ľ���
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
        mBuilder.setContentTitle("���Ա���")
                .setContentText(msg)
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL, mContext))
                // .setNumber(number)//��ʾ����
                .setTicker("����֪ͨ����")
                // ֪ͨ�״γ�����֪ͨ��������������Ч����
                .setWhen(System.currentTimeMillis())
                // ֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
                .setPriority(Notification.PRIORITY_DEFAULT)
                // ���ø�֪ͨ���ȼ�
                // .setAutoCancel(true)//���������־���û��������Ϳ�����֪ͨ���Զ�ȡ��
                .setOngoing(false)
                // ture��������Ϊһ�����ڽ��е�֪ͨ������ͨ����������ʾһ����̨����,�û���������(�粥������)����ĳ�ַ�ʽ���ڵȴ�,���ռ���豸(��һ���ļ�����,ͬ������,������������)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                // ��֪ͨ������������ƺ���Ч������򵥡���һ�µķ�ʽ��ʹ�õ�ǰ���û�Ĭ�����ã�ʹ��defaults���ԣ�������ϣ�
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND ������� //
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
