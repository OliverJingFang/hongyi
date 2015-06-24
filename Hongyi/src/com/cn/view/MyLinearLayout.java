package com.cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    
    private String TAG = "FYL";
    
    public MyLinearLayout(Context context) {
        super(context);
    }
    
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "LinearLayout dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "LinearLayout dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "LinearLayout dispatchTouchEvent ACTION_UP");
                break;
            
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        int action = event.getAction();
        
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "LinearLayout onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "LinearLayout onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "LinearLayout onTouchEvent ACTION_UP");
                break;
            
            default:
                break;
        }
        
        return super.onTouchEvent(event);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "LinearLayout onInterceptTouchEvent ACTION_DOWN");
                break;
                
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "LinearLayout onInterceptTouchEvent ACTION_MOVE");
                break;
                
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "LinearLayout onInterceptTouchEvent ACTION_UP");
                break;
            
            default:
                break;
        }
        
        return super.onInterceptTouchEvent(ev);
    }
    
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.e(TAG, "LinearLayout requestDisallowInterceptTouchEvent ");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
    
}
