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
 * 项目名称：Hongyi 类名称：BackService 类描述： 创建人：hongyi 创建时间：2015年2月10日 上午11:53:09
 * 修改人：hongyi 修改时间：2015年2月10日 上午11:53:09 修改备注：
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
        ToastUtil.showToast(this, "线程开启");
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
