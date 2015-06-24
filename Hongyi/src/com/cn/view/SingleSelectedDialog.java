package com.cn.view;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.cn.hongyi.R;

/**
 * 项目名称：Hongyi 类名称：SingleSelectedDialog 类描述： 创建人：hongyi 创建时间：2015年2月9日 下午4:19:12
 * 修改人：hongyi 修改时间：2015年2月9日 下午4:19:12 修改备注：
 * 
 * @version
 */
public class SingleSelectedDialog extends Dialog implements android.view.View.OnClickListener, OnItemClickListener {
    
    private Context mContext;
    
    private String mTitle;
    
    private TextView mTvTitle;
    
    private Button mBtClose;
    
    private ListView mLvItems;
    
    private ArrayList<String> mStrList;
    
    private onSingleSelectedListenter mSelectedListener;
    
    public SingleSelectedDialog(Context context, String title, ArrayList<String> strList, int theme) {
        super(context, theme);
        this.mContext = context;
        this.mTitle = title;
        this.mStrList = strList;
    }
    
    public SingleSelectedDialog(Context mcContext, int theme) {
        super(mcContext, theme);
    }
    
    public void setSingleSelectedListener(onSingleSelectedListenter selectedListener) {
        this.mSelectedListener = selectedListener;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.view_single_choice, null);
        mTvTitle = (TextView) mView.findViewById(R.id.tv_title);
        mBtClose = (Button) mView.findViewById(R.id.btn_close);
        mLvItems = (ListView) mView.findViewById(R.id.insurance_type_list);
        mLvItems.setOnItemClickListener(this);
        mBtClose.setOnClickListener(this);
        mTvTitle.setText(mTitle);
        
        ArrayAdapter<String> myAdapterInstance = new ArrayAdapter<String>(mContext,
                                                                          android.R.layout.simple_list_item_1,
                                                                          mStrList);
        mLvItems.setAdapter(myAdapterInstance);
        myAdapterInstance.notifyDataSetChanged();
        setContentView(mView);
    }
    
    @Override
    public void onClick(View v) {
        this.dismiss();
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.dismiss();
        mSelectedListener.onItemSelected(mStrList.get(position), position);
       
    }
    
    public interface onSingleSelectedListenter {
        public void onItemSelected(String item, int position);
    }
    
}
