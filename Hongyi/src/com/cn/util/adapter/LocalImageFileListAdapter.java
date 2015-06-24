package com.cn.util.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.hongyi.R;
import com.cn.util.FileUtils;
import com.squareup.picasso.Picasso;

public class LocalImageFileListAdapter extends BaseAdapter {
    
    private Context mContext;
    
    private ArrayList<String> mFileNameLists = new ArrayList<String>();
    
    private HashMap<String, ArrayList<String>> mMapList = new HashMap<String, ArrayList<String>>();
    
    public LocalImageFileListAdapter(Context context,
                                     ArrayList<String> fileList,
                                     HashMap<String, ArrayList<String>> mapList) {
        this.mContext = context;
        this.mFileNameLists = fileList;
        this.mMapList = mapList;
    }
    
    @Override
    public int getCount() {
        return mFileNameLists.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mFileNameLists.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_local_image, null);
            mViewHolder = new ViewHolder();
            mViewHolder.firstImgIv = (ImageView) convertView.findViewById(R.id.local_iv);
            mViewHolder.fileNameTv = (TextView) convertView.findViewById(R.id.local_img_file_name_tv);
            mViewHolder.fileSizeTv = (TextView) convertView.findViewById(R.id.local_img_file_size_tv);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        String imageType = mFileNameLists.get(position);
        List<String> imageList = mMapList.get(imageType);
        Picasso.with(mContext)
               .load(new File(imageList.get(0)))
               .placeholder(R.drawable.add_normal)
               .error(R.drawable.add_normal)
               .fit()
               .into(mViewHolder.firstImgIv);
        mViewHolder.fileNameTv.setText(FileUtils.getLastFileName(imageType));
        mViewHolder.fileSizeTv.setText("(" + imageList.size() + ")");
        return convertView;
    }
    
    class ViewHolder {
        ImageView firstImgIv;
        
        TextView fileNameTv, fileSizeTv;
    }
    
}
