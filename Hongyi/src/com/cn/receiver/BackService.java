package com.cn.receiver;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.cn.util.message.ToastUtil;

/**
 * ��Ŀ���ƣ�Hongyi �����ƣ�BackService �������� �����ˣ�hongyi ����ʱ�䣺2015��2��10�� ����11:53:09
 * �޸��ˣ�hongyi �޸�ʱ�䣺2015��2��10�� ����11:53:09 �޸ı�ע��
 * 
 * @version
 */
public class BackService extends Service {
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    public void MyMethod(){  
        Log.i("HY", "BindService-->MyMethod()");  
    }  
    
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.showToast(this, "�߳̿���");
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                Intent broadCast = new Intent();
                broadCast.putExtra("Size", 1000);
                broadCast.setAction("getBroad");
                sendBroadcast(broadCast);
            }
        }, 5000);
    }
    
    public class MyBinder extends Binder{  
        
        public BackService getService1(){  
            return BackService.this;  
        }  
    }  
    
}
