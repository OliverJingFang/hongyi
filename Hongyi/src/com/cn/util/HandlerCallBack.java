package com.cn.util;

import android.app.Dialog;

public interface HandlerCallBack {

    /**
     * 网络请求回调接口
     * 
     * @author genie
     */
        /**
         * 加载进度视图 可选
         * 
         * @param view
         */
        void onLoading(Dialog view);
        
        /**
         * 成功才调用该方法 返回数据处理
         * 
         * @param result
         *            返回值
         */
        void onSuccess(String result);
        
        /**
         * 客户端请求失败
         * 
         * @param error
         *            错误信息
         */
        void onError(String errMsg);
    }
