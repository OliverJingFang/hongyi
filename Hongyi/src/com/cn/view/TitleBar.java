package com.cn.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.hongyi.R;

/**
 * 项目名称：Hongyi 类名称：TitleBar 类描述： 创建人：hongyi 创建时间：2015年4月2日 上午11:48:35 修改人：hongyi
 * 修改时间：2015年4月2日 上午11:48:35 修改备注：
 * 
 * @version
 */
public class TitleBar extends LinearLayout implements OnClickListener {
    
    public LinearLayout mFinishedLay, afterLoginLay;
    
    TextView mLoginTv, mTitleTv;
    
    FrameLayout mFrameLay;
    
    ImageView BtnOrderlistIv, BtnHomeIv;
    
    private Context mContext;
    
    View mView;
    
    OnTitleBarClickListener mOnTitleBarClickListener;
    
    public TitleBar(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }
    
    private void initView() {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.view_title_bar, this);
        mFinishedLay = (LinearLayout) mView.findViewById(R.id.back_finished_lay);
        afterLoginLay = (LinearLayout) mView.findViewById(R.id.login_in_lay);
        mTitleTv = (TextView) mView.findViewById(R.id.title_name_tv);
        mLoginTv = (TextView) mView.findViewById(R.id.title_login_in_tv);
        mFrameLay = (FrameLayout) mView.findViewById(R.id.left);
        BtnOrderlistIv = (ImageView) mView.findViewById(R.id.iv_title_bar_order);
        BtnHomeIv = (ImageView) mView.findViewById(R.id.iv_title_bar_home);
        mFinishedLay.setOnClickListener(this);
        mLoginTv.setOnClickListener(this);
        BtnOrderlistIv.setOnClickListener(this);
        BtnHomeIv.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_finished_lay:
                mOnTitleBarClickListener.finished();
                break;
            case R.id.title_login_in_tv:
                mOnTitleBarClickListener.login(false);
                break;
            case R.id.iv_title_bar_order:
                mOnTitleBarClickListener.goList();
                break;
            case R.id.iv_title_bar_home:
                mOnTitleBarClickListener.goHome();
                break;
            default:
                break;
        }
    }
    
    public void setOnTitleListener(OnTitleBarClickListener onTitleBarClickListener) {
        this.mOnTitleBarClickListener = onTitleBarClickListener;
    }
    
    public interface OnTitleBarClickListener {
        public void finished();
        
        public void login(boolean isLogined);
        
        public void goList();
        
        public void goHome();
    }
    
    /**
     * @description 设置标题栏的名字
     * @date 2015年4月2日
     * @param
     * @return void
     * @Exception
     */
    public void setTitleName(String titleName) {
        mTitleTv.setText(titleName.toString());
    }
    
    public void setRightTitleName(String nameTxt) {
        mLoginTv.setText(nameTxt.toString());
    }
    
    public void setRightBarInvisible() {
        mFrameLay.setVisibility(View.GONE);
    }
    
    public void isLoginOrOut(boolean isLogin) {
        if (isLogin) {
            afterLoginLay.setVisibility(View.VISIBLE);
            mLoginTv.setVisibility(View.INVISIBLE);
        }
        else {
            afterLoginLay.setVisibility(View.INVISIBLE);
            mLoginTv.setVisibility(View.VISIBLE);
        }
    }
    
}
