package com.cn.util.adapter;

import java.io.File;
import java.util.ArrayList;

import com.cn.hongyi.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class LocalImgDetailAdapter extends BaseAdapter {
    
    private Context mContext;
    
    private ArrayList<String> imageList;
    
    public LocalImgDetailAdapter(Context context, ArrayList<String> images) {
        this.mContext = context;
        this.imageList = images;
    }
    
    @Override
    public int getCount() {
        return imageList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image_view, null);
            mHolder = new ViewHolder();
            mHolder.localImgIv = (ImageView) convertView.findViewById(R.id.image_iv);
            mHolder.selectedSignIv = (ImageView) convertView.findViewById(R.id.image_selected_iv);
            convertView.setTag(mHolder);  
        }
        else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        
        Picasso.with(mContext)
               .load(new File(imageList.get(position)))
               .placeholder(R.drawable.add_normal)
               .error(R.drawable.add_normal)
               .fit()
               .into(mHolder.localImgIv);
        
        return convertView;
    }
    
    class ViewHolder {
        ImageView localImgIv, selectedSignIv;
    }
    
}
