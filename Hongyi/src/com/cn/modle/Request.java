package com.cn.modle;

import com.cn.util.HandlerCallBack;

public class Request {
    
    public HandlerCallBack callback;
    
    /**
     * 请求目的
     */
    public String action;
    
    /**
     * 参数
     */
    public String param;
}
