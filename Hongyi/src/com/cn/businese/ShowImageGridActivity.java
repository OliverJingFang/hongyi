package com.cn.businese;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.GridView;

import com.cn.hongyi.R;
import com.cn.util.adapter.LocalImgDetailAdapter;

public class ShowImageGridActivity extends BaseActivity {
    
    private GridView mGridView;
    
    private ArrayList<String> imgFilePath = new ArrayList<String>();
    
    private LocalImgDetailAdapter mAdapter;
    
    private String mTitle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);
        initData();
        initView();
    }
    
    private void initView() {
        mGridView = (GridView) findViewById(R.id.show_img_gv);
        mAdapter = new LocalImgDetailAdapter(this, imgFilePath);
        mGridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    
    @SuppressWarnings("unchecked")
    private void initData() {
        Bundle mBundle = getIntent().getExtras();
        imgFilePath = (ArrayList<String>) mBundle.getSerializable("imgList");
        mTitle=mBundle.getString("title");
        setTitleName(mTitle);
    }
    
}
