package com.cn.businese;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cn.hongyi.R;
import com.cn.util.FileUtils;
import com.cn.util.adapter.LocalImageFileListAdapter;

/**
 * 项目名称：Hongyi 类名称：LocalImgListActivity 类描述： 创建人：hongyi 创建时间：2015年4月7日 下午4:37:54
 * 修改人：hongyi 修改时间：2015年4月7日 下午4:37:54 修改备注：
 * 
 * @version
 */
public class LocalImgListActivity extends BaseActivity implements OnItemClickListener {
    
    private ListView mImageLv;
    
    private ArrayList<String> mFileNameList = new ArrayList<String>();
    
    private HashMap<String, ArrayList<String>> mMapList = new HashMap<String, ArrayList<String>>();
    
    private LocalImageFileListAdapter mLocalImgAdapter;
    
    private Context mContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_image_file_list);
        mContext = this;
        initData();
        initView();
    }
    
    @SuppressWarnings("unchecked")
    private void initData() {
        Bundle mBundle = getIntent().getExtras();
        mFileNameList = (ArrayList<String>) mBundle.getStringArrayList("localFileNameList");
        mMapList = (HashMap<String, ArrayList<String>>) mBundle.getSerializable("localMap");
    }
    
    private void initView() {
        mImageLv = (ListView) findViewById(R.id.local_img_file_lv);
        mImageLv.setOnItemClickListener(this);
        mLocalImgAdapter = new LocalImageFileListAdapter(mContext, mFileNameList, mMapList);
        mImageLv.setAdapter(mLocalImgAdapter);
        mLocalImgAdapter.notifyDataSetChanged();
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<String> imgList = mMapList.get(mFileNameList.get(position));
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("imgList", imgList);
        mBundle.putString("title", FileUtils.getLastFileName(mFileNameList.get(position)));
        qStartActivity(ShowImageGridActivity.class, mBundle);
    }
    
    private HashMap<String, ArrayList<String>> sortFile(ArrayList<String> fileNameList,
                                                        HashMap<String, ArrayList<String>> mMapList) {
        HashMap<String, ArrayList<String>> mResultList = new HashMap<String, ArrayList<String>>();
        int size = fileNameList.size();
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                String iKey = FileUtils.getLastFileName(fileNameList.get(i));
                String jKey = FileUtils.getLastFileName(fileNameList.get(j));
                if (iKey.equalsIgnoreCase(jKey)) {
                    
                }
            }
        }
        return mResultList;
    }
    
}
