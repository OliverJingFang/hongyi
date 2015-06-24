package com.cn.businese;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cn.hongyi.R;
import com.cn.util.HandlerCallBack;
import com.cn.util.JSONUtils;
import com.cn.util.net.HttpRequest;
import com.cn.view.pulluprefresh.PullToRefreshBase;
import com.cn.view.pulluprefresh.PullToRefreshBase.OnRefreshListener;
import com.cn.view.pulluprefresh.PullToRefreshListView;

public class RreshActivity extends Activity implements OnRefreshListener<ListView> {
    
    private PullToRefreshListView mPullRefeshLv;
    
    private ListView mLv;
    
    private LinkedList<String> listItems;
    
    private ArrayAdapter<String> mAdapter;
    
    private Context mContext;
    
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initView();
    }
    
    private void initView() {
        mContext = this;
        mPullRefeshLv = (PullToRefreshListView) findViewById(R.id.lv_refresh);
        
        mPullRefeshLv.setPullLoadEnabled(true);
        mPullRefeshLv.setScrollLoadEnabled(false);
        
        mPullRefeshLv.setOnRefreshListener(this);
        mLv = mPullRefeshLv.getRefreshableView();
        listItems = new LinkedList<String>();
        listItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listItems);
        mLv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        setLastUpdateTime();
        mPullRefeshLv.doPullRefreshing(true, 500);
    }
    
    /**
     * 下拉刷新响应函数
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        getUpdata();
    }
    
    /**
     * 上啦加载响应函数
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        getDownData();
    }
    
    private void getUpdata() {
        HttpRequest.request(mContext, "", "pulldown", new HandlerCallBack() {
            
            @Override
            public void onSuccess(String result) {
                List<String> cities = JSONUtils.parseCities(result);
                mPullRefeshLv.setHasMoreData(true);
                listItems.addAll(0, cities);
                mAdapter.notifyDataSetChanged();
                mPullRefeshLv.onPullDownRefreshComplete();
                setLastUpdateTime();
            }
            
            @Override
            public void onLoading(Dialog view) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onError(String errMsg) {
                // TODO Auto-generated method stub
            }
        });
    }
    
    private void getDownData() {
        HttpRequest.request(mContext, "", "pullup", new HandlerCallBack() {
            
            @Override
            public void onSuccess(String result) {
                List<String> cities = JSONUtils.parseCities(result);
                listItems.addAll(cities);
                mPullRefeshLv.onPullUpRefreshComplete();
                mAdapter.notifyDataSetChanged();
                setLastUpdateTime();
            }
            
            @Override
            public void onLoading(Dialog view) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onError(String errMsg) {
                // TODO Auto-generated method stub
                
            }
        });
    }
    
    private void setLastUpdateTime() {
        String text = formatDateTime(System.currentTimeMillis());
        mPullRefeshLv.setLastUpdatedLabel(text);
    }
    
    private String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }
        return mDateFormat.format(new Date(time));
    }
    
    public static final String[] mStrings = { "四川",
                                             "重庆",
                                             "贵州",
                                             "广西",
                                             "广东",
                                             "纽约",
                                             "洛杉矶",
                                             "迈阿密",
                                             "休斯顿",
                                             "克利夫兰",
                                             "多伦多",
                                             "旧金山" };
    
}
