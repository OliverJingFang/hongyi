package com.cn;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

import com.baidu.mapapi.SDKInitializer;

public class HyApp extends Application {
    
    public static HashMap<String, ArrayList<String>> mImageMapList = new HashMap<String, ArrayList<String>>();
    
    public static HyApp mContext;
    
    /** 单列模式获取INSTANCE */
    public static HyApp getInstance() {
        return mContext;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        SDKInitializer.initialize(getApplicationContext());
        JPushInterface.init(getApplicationContext());
        JPushInterface.setDebugMode(true);
    }
}
