package com.cn.util.pull;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.cn.businese.TestActivity;
import com.cn.hongyi.R;

public class PullingService extends Service {

        public static final String ACTION = "com.ryantang.service.PollingService";
        
        private Notification mNotification;
        private NotificationManager mManager;
        int count = 0;

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            initNotifiManager();
        }
        
        @Override
        public void onStart(Intent intent, int startId) {
            //new PollingThread().start();
           
                    Log.i("FYL", "Polling...");
                    count ++;
                    //�������ܱ�5����ʱ����֪ͨ
                    if (count % 2 == 0) {
                        showNotification();
                        Log.i("FYL", "New message!");
                    }
        }

        //��ʼ��֪ͨ������
        private void initNotifiManager() {
            mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int icon = R.drawable.ic_launcher;
            mNotification = new Notification();
            mNotification.icon = icon;
            mNotification.tickerText = "New Message";
            mNotification.defaults |= Notification.DEFAULT_SOUND;
            mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        }

        //����Notification
        private void showNotification() {
            mNotification.when = System.currentTimeMillis();
            //Navigator to the new activity when click the notification title
            Intent i = new Intent(this, TestActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            mNotification.setLatestEventInfo(this,
                    getResources().getString(R.string.app_name), "You have new message!", pendingIntent);
            mManager.notify(0, mNotification);
        }

        /**
         * Polling thread
         * ģ����Server��ѯ���첽�߳�
         * @Author Ryan
         * @Create 2013-7-13 ����10:18:34
         */
       /* int count = 0;
        class PollingThread extends Thread {
            @Override
            public void run() {
                Log.i("FYL", "Polling...");
                count ++;
                //�������ܱ�5����ʱ����֪ͨ
                if (count % 2 == 0) {
                    showNotification();
                    Log.i("FYL", "New message!");
                }
            }
        }*/
        
        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i("FYL", "Service:onDestroy");
        }

    }
