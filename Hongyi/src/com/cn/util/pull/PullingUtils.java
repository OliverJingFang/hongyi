package com.cn.util.pull;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * 项目名称：Hongyi 类名称：PullingUtils 类描述： 定期轮询基础类 创建人：hongyi 创建时间：2015年6月19日
 * 上午9:59:02 修改人：hongyi 修改时间：2015年6月19日 上午9:59:02 修改备注：
 * 
 * @version
 */
public class PullingUtils {
    
    // 开启轮询服务
    public static void startPollingService(Context context, int seconds, Class<?> cls, String action) {
        // 获取AlarmManager系统服务
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        
        // 包装需要执行Service的Intent
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        
        // 触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();
        
        // 使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, seconds * 1000, pendingIntent);
    }
    
    // 停止轮询服务
    public static void stopPollingService(Context context, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 取消正在执行的服务
        manager.cancel(pendingIntent);
    }
    
}
