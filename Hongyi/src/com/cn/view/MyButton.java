package com.cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button {
    
    private String TAG = "FYL";
    
    public MyButton(Context context) {
        super(context);
    }
    
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyButton dispatchKeyEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyButton dispatchKeyEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyButton dispatchKeyEvent ACTION_UP");
                break;
            
            default:
                break;
        }
        return super.dispatchKeyEvent(event);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyButton dispatchTouchEvent ACTION_DOWN");
                //break;
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyButton dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyButton dispatchTouchEvent ACTION_UP");
                break;
            
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyButton onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyButton onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyButton onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    
}
