package com.cn.businese;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.cn.hongyi.R;
import com.cn.view.MyButton;

public class TestActivity extends Activity implements OnClickListener {
    
    private Button mButton;
    private Timer mTimer;
    private String TAG = "FYL";
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_test);
        initView();
        mTimer = new Timer(true);
        mTimer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                Log.i(TAG, "aaaaa");
            }
        }, 1000,1000);
    }
    

    
    private void initView() {
        mButton = (MyButton) findViewById(R.id.my_button);
        mButton.setOnClickListener(this);
        mButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "TestActivity onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "TestActivity onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "TestActivity onTouch ACTION_UP");
                        break;
                    default:
                        break;
                }
                
                return false;
            }
        });
    }
    
    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick");
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "TestActivity dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "TestActivity dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "TestActivity dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    
}
