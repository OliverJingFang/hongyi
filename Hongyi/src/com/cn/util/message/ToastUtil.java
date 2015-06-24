package com.cn.util.message;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    
    public static  void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
    
}
