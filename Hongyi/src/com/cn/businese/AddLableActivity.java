package com.cn.businese;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.hongyi.R;
import com.cn.util.DisplayUtils;

/**
 * ��Ŀ���ƣ�Hongyi �����ƣ�AddLableActivity �������� �����ˣ�hongyi ����ʱ�䣺2015��1��14�� ����4:06:38
 * �޸��ˣ�hongyi �޸�ʱ�䣺2015��1��14�� ����4:06:38 �޸ı�ע��
 * 
 * @version
 */
public class AddLableActivity extends Activity {
    
    Button BtAddLable;
    
    LinearLayout LayLaybleContent;
    
    Context mContext;
    
    int i = 0;
    
    boolean hasMeasured = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlable);
        initView();
    }
    
    private void initView() {
        mContext = this;
        BtAddLable = (Button) findViewById(R.id.bt_add_lable);
        LayLaybleContent = (LinearLayout) findViewById(R.id.main_lay);
        BtAddLable.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                
                /** �ж��Ƿ�����View */
                final int ViewCount = LayLaybleContent.getChildCount();
                if (ViewCount == 0) {
                    LinearLayout firstLay = getLineaLayout(LinearLayout.HORIZONTAL);
                    firstLay.addView(getAvalableLay("С��" + (i++), firstLay));
                    LayLaybleContent.addView(firstLay);
                }
                else {
                    LinearLayout lastView = (LinearLayout) LayLaybleContent.getChildAt(ViewCount - 1);
                    final LinearLayout lableLay = getAvalableLay("С��" + (i++), lastView);
                    
                    final TextView mTv=new TextView(mContext);
                    mTv.setText("20000");
                    mTv.setTextColor(Color.RED);
                    
                   
                    LayLaybleContent.postDelayed(new Runnable() {
                        
                        @Override
                        public void run() {
                            int lableLayWidth = mTv.getMeasuredWidth();
                            Log.d("HY", "lableLayWidth: " + lableLayWidth);
                            lableLayWidth = mTv.getWidth();
                            Log.d("HY", "lableLayWidth: " + lableLayWidth);
                        }
                    }, 300);
                    
      /*              ViewTreeObserver vto = lableLay.getViewTreeObserver();
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        
                        @Override
                        public boolean onPreDraw() {
                            if (hasMeasured == false) {
                                int lableLayWidth = lableLay.getMeasuredWidth();
                                Log.d("HY", "height: " + lableLayWidth);
                                hasMeasured = true;
                            }
                            return true;
                        }
                    });*/
                    
                    
                    /*
                     * int avalableWidth = 0; int lastViewChildCout =
                     * lastView.getChildCount(); for (int i = 0; i <
                     * lastViewChildCout; i++) { avalableWidth +=
                     * lastView.getChildAt(i).getMeasuredWidth(); } if
                     * (lableLayWidth < (avalableWidth - lastViewChildCout *
                     * DisplayUtils.dp2px(mContext, 5))) {
                     * lastView.addView(lableLay); } else { LinearLayout
                     * firstLay = getLineaLayout(LinearLayout.HORIZONTAL);
                     * LinearLayout mlableLay = getAvalableLay("С��" + (i++),
                     * firstLay); firstLay.addView(mlableLay);
                     * LayLaybleContent.addView(firstLay); }
                     */
                }
            }
        });
    }
    
    /**
     * ��ȡ���в��ֶ���
     * 
     * @description
     * @date 2015��1��15��
     * @param
     * @return View
     * @Exception
     */
    @SuppressLint("InlinedApi")
    private LinearLayout getLineaLayout(int orientation) {
        LinearLayout mLineaLay = new LinearLayout(mContext);
        mLineaLay.setOrientation(orientation);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                                                                          LayoutParams.MATCH_PARENT);
        mLineaLay.setLayoutParams(mParams);
        return mLineaLay;
    }
    
    @SuppressLint("InflateParams")
    private LinearLayout getAvalableLay(String text, final View parentView) {
        final LinearLayout mLay = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_lable, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                     LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                     1); // ,
                                                                         // 1�ǿ�ѡд��
        
        lp.setMargins(DisplayUtils.dp2px(mContext, 5),
                      0,
                      DisplayUtils.dp2px(mContext, 5),
                      DisplayUtils.dp2px(mContext, 5));
        
        TextView mTvLable = (TextView) mLay.findViewById(R.id.tv_lable_name);
        ImageView mIvDelete = (ImageView) mLay.findViewById(R.id.iv_delete_lable);
        mTvLable.setText(text + (i++));
        
        mIvDelete.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ((ViewGroup) parentView).removeView(mLay);
            }
        });
        return mLay;
    }
}
