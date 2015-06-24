package com.cn.util;

public class StringUtils {
    
    public static boolean isEmpty(String msg) {
        boolean result = false;
        if (msg == null) {
            result = true;
        }
        else if ("".equalsIgnoreCase(msg.trim())) {
            result = true;
        }
        
        return result;
    }
}
