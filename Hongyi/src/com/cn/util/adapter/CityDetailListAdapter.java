package com.cn.util.adapter;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cn.hongyi.R;

public class CityDetailListAdapter extends BaseAdapter {
    
    private Context mContext;
    
    private ArrayList<String> mCityList;
    
    public CityDetailListAdapter(Context context, ArrayList<String> cityList) {
        this.mContext = context;
        this.mCityList = cityList;
    }
    
    @Override
    public int getCount() {
        return mCityList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mCityList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_city_list, null);
            mViewHolder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.textView.setText(mCityList.get(position));
        return convertView;
    }
    
    class ViewHolder {
        TextView textView;
    }
    
}
