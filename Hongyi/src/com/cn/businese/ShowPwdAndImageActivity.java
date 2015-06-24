package com.cn.businese;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cn.hongyi.R;
import com.cn.util.FileUtils;

public class ShowPwdAndImageActivity extends BaseActivity implements OnClickListener {
    
    private EditText mPwdEt;
    
    private Button mShowPwdBt, mGetImgBt;
    
    private boolean mIsShow = false;
    
    private String content = "";
    
    private HashMap<String, ArrayList<String>> mImageMapList = new HashMap<String, ArrayList<String>>();
    
    private ArrayList<String> mPathList = new ArrayList<String>();
    
    /*
     * if (!StringUtils.isEmpty(mHotelDetailBaseInfo.pictureURL)) {
     * Picasso.with(mContext) .load(mHotelDetailBaseInfo.pictureURL)
     * .placeholder(R.drawable.login_bg) .error(R.drawable.login_bg)
     * .into(mIvHotelPicutre);}
     */
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pwd_image);
        initView();
        new Thread(new GetImageFilePathThread()).start();
    }
    
    private void initView() {
        mPwdEt = (EditText) findViewById(R.id.my_pwd_et);
        mShowPwdBt = (Button) findViewById(R.id.my_sub_bt);
        mGetImgBt = (Button) findViewById(R.id.my_get_img);
        mGetImgBt.setVisibility(View.INVISIBLE);
        mShowPwdBt.setOnClickListener(this);
        mGetImgBt.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_sub_bt:
                showPwdContent();
                break;
            
            case R.id.my_get_img:
                Intent mIntent = new Intent();
                Bundle mBundle = new Bundle();
                mBundle.putStringArrayList("localFileNameList", mPathList);
                mBundle.putSerializable("localMap", mImageMapList);
                mIntent.putExtras(mBundle);
                mIntent.setClass(this, LocalImgListActivity.class);
                startActivity(mIntent);
                break;
            
            default:
                break;
        }
    }
    
    private void showPwdContent() {
        content = mPwdEt.getText().toString();
        if (mIsShow) {
            mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else {
            mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        mIsShow = !mIsShow;
        mPwdEt.setSelection(content.length());
    }
    
    Handler handler = new Handler() {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public void handleMessage(Message msg) {
            mGetImgBt.setVisibility(View.VISIBLE);
            Iterator it = mImageMapList.entrySet().iterator();
            while (it.hasNext()) {
                // System.out.println("Ã»ÓÐÔÝÍ£");
                Map.Entry entry = (Map.Entry) it.next();
                String filePath = (String) entry.getKey();
                ArrayList<String> bitmapPathList = (ArrayList<String>) entry.getValue();
                Log.i("FJ", "filePath: " + filePath);
                for (int i = 0, size = bitmapPathList.size(); i < size; i++) {
                    Log.i("FJ", "image: " + bitmapPathList.get(i));
                }
            }
            super.handleMessage(msg);
        }
    };
    
    private class GetImageFilePathThread implements Runnable {
        @Override
        public void run() {
            File file = new File(Environment.getExternalStorageDirectory() + "");
            getFileList(file);
            Message msg = handler.obtainMessage();
            msg.what = 1;
            handler.sendMessage(msg);
        }
        
    }
    
    private void getFileList(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    if (".png".equals(FileUtils.getFileNameEnds(f)) || (".jpg".equals(FileUtils.getFileNameEnds(f)))) {
                        if (!mImageMapList.containsKey(file.getAbsolutePath())) {
                            mPathList.add(file.getAbsolutePath());
                            ArrayList<String> imageList = new ArrayList<String>();
                            imageList.add(f.getAbsolutePath());
                            mImageMapList.put(file.getAbsolutePath(), imageList);
                        }
                        else {
                            mImageMapList.get(file.getAbsolutePath()).add(f.getAbsolutePath());
                        }
                    }
                }
                else {
                    getFileList(f);
                }
            }
        }
    }
    

}
