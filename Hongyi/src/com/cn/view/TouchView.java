package com.cn.view;

import com.cn.hongyi.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TouchView extends View implements OnClickListener {
    
    private PopupWindow mPopupWindow;
    
    private TextView mPopupText;
    
    private Paint mPaint = new Paint();
    
    private Handler handler = new Handler();
    
    public TouchView(Context context) {
        super(context);
        setOnClickListener(this);
    }
    
    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }
    
    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showPopup("d");
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                showPopup("m");
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                dismissPopup();
                invalidate();
                break;
                
        }
        return true;
    }
    
    private void showPopup(String textStr) {
        if (mPopupWindow == null) {
            handler.removeCallbacks(dismissRunnable);
            mPopupText = new TextView(getContext());
            mPopupText.setBackgroundColor(Color.GRAY);
            mPopupText.setTextColor(Color.CYAN);
            mPopupText.setTextSize(18);
            mPopupText.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            mPopupWindow = new PopupWindow(mPopupText, 110, 110);
        }
        mPopupText.setText(textStr);
        if (mPopupWindow.isShowing()) {
            mPopupWindow.update();
        }
        else {
            mPopupWindow.showAtLocation(getRootView(), Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        }
    }
    
    private void dismissPopup() {
        handler.postDelayed(dismissRunnable, 500);
    }
    
    Runnable dismissRunnable = new Runnable() {
        
        @Override
        public void run() {
            if (mPopupWindow != null) {
                mPopupWindow.dismiss();
            }
        }
    };
    
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(R.color.blue);
        mPaint.setColor(Color.GREEN);
        mPaint.setFakeBoldText(true);
        mPaint.setTextSize(40f);
        mPaint.setAntiAlias(true);
        canvas.drawText("中兴", getWidth() / 2-mPaint.measureText("中兴")/2, getHeight() / 2, mPaint);
    };
    
    @Override
    public void onClick(View v) {
        showPopup("点");
        invalidate();  
    };
    
}
