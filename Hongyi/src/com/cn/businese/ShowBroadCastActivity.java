package com.cn.businese;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.cn.hongyi.R;
import com.cn.receiver.BackService;
import com.cn.receiver.BackService.MyBinder;
import com.cn.util.message.ToastUtil;

/**
 * 项目名称：Hongyi 类名称：ShowBroadCastActivity 类描述： 创建人：hongyi 创建时间：2015年2月10日
 * 上午11:35:25 修改人：hongyi 修改时间：2015年2月10日 上午11:35:25 修改备注：
 * 
 * @version
 */
public class ShowBroadCastActivity extends Activity {
    
    private ShowBroadCastReceiver mReceiver;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_broadcast);
        initView();
    }
    
    private void initView(){
        mReceiver = new ShowBroadCastReceiver();
        registerReceiver(mReceiver, new IntentFilter("getBroad"));
        Intent intent = new Intent(ShowBroadCastActivity.this,BackService.class);  
        bindService(intent, conn, Context.BIND_AUTO_CREATE);  
    }
    
    class ShowBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int updateSize = intent.getIntExtra("Size", 0);
            ToastUtil.showToast(context, "你传过来的值为：" + updateSize);
        }
    }
    
    private ServiceConnection conn = new ServiceConnection() {  
        
        @Override  
        public void onServiceDisconnected(ComponentName name) {  
        }  
          
        @Override  
        public void onServiceConnected(ComponentName name, IBinder service) {  
            MyBinder binder = (MyBinder)service;  
            BackService bindService = binder.getService1();  
            bindService.MyMethod();  
            Log.i("HY", "连接上了。。。。。");
        }  
    };  
    
   protected void onDestroy() {
       super.onDestroy();
       unregisterReceiver(mReceiver);
       unbindService(conn);
   }; 
   
}
