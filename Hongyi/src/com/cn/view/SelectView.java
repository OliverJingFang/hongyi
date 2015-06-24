package com.cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.cn.util.DisplayUtils;

public class SelectView extends RelativeLayout {
    
    private Scroller mScroller;
    
    private int mScreenHeigh = 0;
    
    private int mDownY = 0;
    
    private int mMoveY = 0;
    
    private int mMoveDis = 0;
    
    private boolean isShowing = false;
    
    private Context mContext;
    
    private int mDuration = 800;
    
    public SelectView(Context context) {
        super(context);
        init(context);
    }
    
    public SelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    
    public SelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }
    
    public void init(Context mContext) {
        setFocusable(true);
        this.mContext = mContext;
        mScroller = new Scroller(mContext);
        mScreenHeigh = DisplayUtils.getWindowHeigh(mContext);
        scrollTo(0, mScreenHeigh);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) event.getY();
                break;
            
            case MotionEvent.ACTION_MOVE:
                mMoveY = (int) event.getY();
                mMoveDis = mMoveY - mDownY;
                if (mMoveDis > 0) {
                    if (isShowing) {
                        scrollTo(0, -Math.abs(mMoveDis));
                    }
                }
                else {
                    if (mScreenHeigh - this.getTop() <= DisplayUtils.getWindowHeigh(mContext) / 2 && !isShowing) {
                        scrollTo(0, Math.abs(DisplayUtils.getWindowHeigh(mContext) / 2 - mMoveDis));
                    }
                }
                break;
                
            case MotionEvent.ACTION_UP:
                if(isShowing){
                    if( this.getScrollY() <= -(DisplayUtils.getWindowHeigh(mContext) / 2/2)){
                        startMoveAnim(this.getScrollY(),-(DisplayUtils.getWindowHeigh(mContext) / 2 - this.getScrollY()), mDuration);
                        isShowing = false;
                    } else {
                        startMoveAnim(this.getScrollY(), -this.getScrollY(), mDuration);
                        isShowing = true;
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
    
    public void startMoveAnim(int startY, int dy, int duration) {
        mScroller.startScroll(0, startY, 0, dy, duration);
        invalidate();
    }
    
}
