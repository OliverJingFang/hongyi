package com.cn.businese;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.cn.hongyi.R;
import com.cn.util.adapter.CityDetailListAdapter;
import com.cn.util.message.ToastUtil;

public class CityListActivity extends Activity implements OnCheckedChangeListener {
    
    RadioGroup mRg;
    
    ListView mLv;
    
    Context mContext;
    
    CityDetailListAdapter mCityListAdapter;
    
    private ArrayList<String> characteristicList = new ArrayList<String>();
    
    private ArrayList<String> brandList = new ArrayList<String>();
    
    private ArrayList<String> airportList = new ArrayList<String>();
    
    private ArrayList<String> commerList = new ArrayList<String>();
    
    private ArrayList<String> adminList = new ArrayList<String>();
    
    private ArrayList<String> subwayList = new ArrayList<String>();
    
    private ArrayList<String> topList = new ArrayList<String>();
    
    private ArrayList<String> underList = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initView();
    }
    
    private void initView() {
        mContext = this;
        characteristicList.add("温泉酒店");
        characteristicList.add("亲子出游");
        characteristicList.add("休闲度假");
        characteristicList.add("购物便捷");
        characteristicList.add("精品酒店");
        characteristicList.add("客栈民宿");
        characteristicList.add("度假公寓");
        
        brandList.add("7天");
        brandList.add("布丁");
        brandList.add("华住(汉庭)");
        brandList.add("金广快捷");
        brandList.add("锦江之星");
        brandList.add("莫泰");
        brandList.add("全季");
        brandList.add("如家");
        brandList.add("速8");
        brandList.add("星程");
        brandList.add("犀浦大酒店");
        
        airportList.add("双流国际机场");
        airportList.add("成都站");
        airportList.add("成都南站");
        airportList.add("成都东站");
        airportList.add("成都城北客运中心");
        airportList.add("成都新南门车站");
        airportList.add("成都石羊客运站");
        airportList.add("成都昭觉寺运站");
        airportList.add("成都汽车运站");
        
        commerList.add("春熙路、大慈寺商业区");
        commerList.add("天府广场");
        commerList.add("中医学院");
        commerList.add("机场地区");
        commerList.add("世纪城新会展");
        commerList.add("火车南站");
        commerList.add("一品天下/金沙遗址附近地区");
        commerList.add("合江亭/九眼桥附近地区");
        commerList.add("华西医院");
        
        adminList.add("锦江区");
        adminList.add("青羊区");
        adminList.add("金牛区");
        adminList.add("武侯区");
        adminList.add("成华区");
        adminList.add("龙泉驿区");
        adminList.add("青白江区");
        adminList.add("新都区");
        adminList.add("温江区");
        adminList.add("都江堰市");
        
        subwayList.add("1号线");
        subwayList.add("2号线");
        subwayList.add("3号线");
        subwayList.add("4号线");
        subwayList.add("5号线");
        subwayList.add("6号线");
        subwayList.add("7号线");
        
        topList.add("大学周边");
        topList.add("医院周边");
        topList.add("景点周边");
        topList.add("会展中心");
        topList.add("高考周边");
        topList.add("高尔夫周边");
        topList.add("温泉周边");
        
        underList.add("都江堰");
        underList.add("郫县");
        underList.add("邛崃");
        underList.add("温江");
        underList.add("青白江");
        
        mRg = (RadioGroup) findViewById(R.id.main_rg);
        mLv = (ListView) findViewById(R.id.city_detail_list);
        
        TextView mTv = new TextView(mContext);
        mTv.setText("Header");
        mTv.setPadding(0, 10, 0, 10);
        mTv.setBackgroundColor(mContext.getResources().getColor(R.color.black));
        mLv.addHeaderView(mTv);
        mRg.setOnCheckedChangeListener(this);
        mCityListAdapter = new CityDetailListAdapter(mContext, characteristicList);
        mLv.setAdapter(mCityListAdapter);
        mCityListAdapter.notifyDataSetChanged();
        
        Log.e("HY", "childCount: "+mLv.getChildCount());
        
        // LayoutInflater
        // mINfInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        /** 特色 */
            case R.id.rb_characteristic:
                ToastUtil.showToast(mContext, "特色");
                mCityListAdapter = new CityDetailListAdapter(mContext, characteristicList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                
                Log.e("HY", "特色");
                break;
            
            /** 品牌 */
            case R.id.rb_brand:
                ToastUtil.showToast(mContext, "品牌");
                mCityListAdapter = new CityDetailListAdapter(mContext, brandList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                Log.e("HY", "品牌");
                break;
            
            /** 商业区 */
            case R.id.rb_commercial_district:
                mCityListAdapter = new CityDetailListAdapter(mContext, commerList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** 机场车站 */
            case R.id.rb_airport_station:
                mCityListAdapter = new CityDetailListAdapter(mContext, airportList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** 行政区 */
            case R.id.rb_administrative_area:
                mCityListAdapter = new CityDetailListAdapter(mContext, adminList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** 地铁线 */
            case R.id.rb_subway_line:
                mCityListAdapter = new CityDetailListAdapter(mContext, subwayList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** 热门地标 */
            case R.id.rb_top_landmarks:
                mCityListAdapter = new CityDetailListAdapter(mContext, topList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** 下辖市区 */
            case R.id.rb_under_provice:
                mCityListAdapter = new CityDetailListAdapter(mContext, underList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
        }
    }
}
