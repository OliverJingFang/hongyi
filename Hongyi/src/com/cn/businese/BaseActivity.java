package com.cn.businese;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.cn.view.TitleBar;
import com.cn.view.TitleBar.OnTitleBarClickListener;

public class BaseActivity extends FragmentActivity implements OnTitleBarClickListener {
    
    /** 顶部栏 */
    public TitleBar mTitleBar;
    
    /** 根布局 */
    private ViewGroup mRoot;
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }
    
    @SuppressWarnings("deprecation")
    @SuppressLint("InlinedApi")
    @Override
    public void setContentView(int layoutResID) {
        mTitleBar = new TitleBar(this);
        mTitleBar.setOnTitleListener(this);
        mRoot = genRootView();
        View mView = LayoutInflater.from(this).inflate(layoutResID, null);
        mRoot.addView(this.mTitleBar, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        mRoot.addView(mView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        super.setContentView(mRoot);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mTitleBar.isLoginOrOut(false);
        
    }
    
    public ViewGroup genRootView() {
        LinearLayout localLinearLayout = new LinearLayout(this);
        localLinearLayout.setOrientation(LinearLayout.VERTICAL);
        return localLinearLayout;
    }
    
    @Override
    public void finished() {
        hideSoftInput(mTitleBar.mFinishedLay.getWindowToken());
        finish();
    }
    
    /**
     * 
     * @description 设置标题栏题目
     * @date 2015年4月2日 
     * @param 
     * @return void
     * @Exception
     */
    public void setTitleName(Object textStr) {
        mTitleBar.setTitleName(textStr.toString());
    }
    
    /**
     * 
     * @description 设置右上角测标题栏题目
     * @date 2015年4月2日 
     * @param 
     * @return void
     * @Exception
     */
    public void setRightBarName(Object textStr) {
        mTitleBar.setRightTitleName(textStr.toString());
    }
    
    @Override
    public void goList() {
        qStartActivity(RreshActivity.class);
    }
    
    @Override
    public void goHome() {
        qStartActivity(RreshActivity.class);
    }
    
    @Override
    public void login(boolean isLogined) {
            qStartActivity(RreshActivity.class);
    }
    
    /**
     * 多种隐藏软件盘方法的其中一种
     * 
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    
    protected void qStartActivity(Class<? extends Activity> resultClass) {
        qStartActivity(resultClass, null);
    }
    
    protected void qStartActivity(Class<? extends Activity> resultClass, Bundle resultBundle) {
        Intent mIntent = new Intent();
        if (resultBundle != null) {
            mIntent.putExtras(resultBundle);
        }
        mIntent.setClass(this, resultClass);
        startActivity(mIntent);
    }
    
    protected void qStartActivityForResult(Class<? extends Activity> resultClass, Bundle resultBundle, int requestCode) {
        Intent mIntent = new Intent();
        if (resultClass != null) {
            mIntent.putExtras(resultBundle);
        }
        mIntent.setClass(this, resultClass);
        startActivityForResult(mIntent, requestCode);
    }
    
    public void qSetResult(Bundle bundle) {
        Intent localIntent = new Intent();
        if (null != bundle) {
            localIntent.putExtras(bundle);
        }
        setResult(RESULT_OK, localIntent);
        BaseActivity.this.finish();
    }
    
}
