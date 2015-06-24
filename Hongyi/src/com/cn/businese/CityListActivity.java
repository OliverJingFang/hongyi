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
        characteristicList.add("��Ȫ�Ƶ�");
        characteristicList.add("���ӳ���");
        characteristicList.add("���жȼ�");
        characteristicList.add("������");
        characteristicList.add("��Ʒ�Ƶ�");
        characteristicList.add("��ջ����");
        characteristicList.add("�ȼٹ�Ԣ");
        
        brandList.add("7��");
        brandList.add("����");
        brandList.add("��ס(��ͥ)");
        brandList.add("�����");
        brandList.add("����֮��");
        brandList.add("Ī̩");
        brandList.add("ȫ��");
        brandList.add("���");
        brandList.add("��8");
        brandList.add("�ǳ�");
        brandList.add("Ϭ�ִ�Ƶ�");
        
        airportList.add("˫�����ʻ���");
        airportList.add("�ɶ�վ");
        airportList.add("�ɶ���վ");
        airportList.add("�ɶ���վ");
        airportList.add("�ɶ��Ǳ���������");
        airportList.add("�ɶ������ų�վ");
        airportList.add("�ɶ�ʯ�����վ");
        airportList.add("�ɶ��Ѿ�����վ");
        airportList.add("�ɶ�������վ");
        
        commerList.add("����·���������ҵ��");
        commerList.add("�츮�㳡");
        commerList.add("��ҽѧԺ");
        commerList.add("��������");
        commerList.add("���ͳ��»�չ");
        commerList.add("����վ");
        commerList.add("һƷ����/��ɳ��ַ��������");
        commerList.add("�Ͻ�ͤ/�����Ÿ�������");
        commerList.add("����ҽԺ");
        
        adminList.add("������");
        adminList.add("������");
        adminList.add("��ţ��");
        adminList.add("�����");
        adminList.add("�ɻ���");
        adminList.add("��Ȫ����");
        adminList.add("��׽���");
        adminList.add("�¶���");
        adminList.add("�½���");
        adminList.add("��������");
        
        subwayList.add("1����");
        subwayList.add("2����");
        subwayList.add("3����");
        subwayList.add("4����");
        subwayList.add("5����");
        subwayList.add("6����");
        subwayList.add("7����");
        
        topList.add("��ѧ�ܱ�");
        topList.add("ҽԺ�ܱ�");
        topList.add("�����ܱ�");
        topList.add("��չ����");
        topList.add("�߿��ܱ�");
        topList.add("�߶����ܱ�");
        topList.add("��Ȫ�ܱ�");
        
        underList.add("������");
        underList.add("ۯ��");
        underList.add("����");
        underList.add("�½�");
        underList.add("��׽�");
        
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
        /** ��ɫ */
            case R.id.rb_characteristic:
                ToastUtil.showToast(mContext, "��ɫ");
                mCityListAdapter = new CityDetailListAdapter(mContext, characteristicList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                
                Log.e("HY", "��ɫ");
                break;
            
            /** Ʒ�� */
            case R.id.rb_brand:
                ToastUtil.showToast(mContext, "Ʒ��");
                mCityListAdapter = new CityDetailListAdapter(mContext, brandList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                Log.e("HY", "Ʒ��");
                break;
            
            /** ��ҵ�� */
            case R.id.rb_commercial_district:
                mCityListAdapter = new CityDetailListAdapter(mContext, commerList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** ������վ */
            case R.id.rb_airport_station:
                mCityListAdapter = new CityDetailListAdapter(mContext, airportList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** ������ */
            case R.id.rb_administrative_area:
                mCityListAdapter = new CityDetailListAdapter(mContext, adminList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** ������ */
            case R.id.rb_subway_line:
                mCityListAdapter = new CityDetailListAdapter(mContext, subwayList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** ���ŵر� */
            case R.id.rb_top_landmarks:
                mCityListAdapter = new CityDetailListAdapter(mContext, topList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
            
            /** ��Ͻ���� */
            case R.id.rb_under_provice:
                mCityListAdapter = new CityDetailListAdapter(mContext, underList);
                mLv.setAdapter(mCityListAdapter);
                mCityListAdapter.notifyDataSetChanged();
                break;
        }
    }
}
