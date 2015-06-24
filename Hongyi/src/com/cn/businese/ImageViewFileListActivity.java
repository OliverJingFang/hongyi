/*package com.cn.businese;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cn.hongyi.R;

@SuppressLint("HandlerLeak")
public class ImageViewFileListActivity extends Activity implements OnItemClickListener {
    
    private ListView image_list = null;
    
    public ImageListFileAdapter adapter = null;
    
    private ArrayList<String> imagePathList = null;
    
    private ArrayList<String> bitMapList = null;
    
    private HashMap<String, String> imageMap = new HashMap<String, String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.image_list);
        
        initView();
        
        System.out.println("========ImageViewFileListActivity============oncreate-----");
        
    }
    
    private void initView() {
        
        image_list = (ListView) findViewById(R.id.image_file_list);
        
        imagePathList = new ArrayList<String>();
        
        bitMapList = new ArrayList<String>();
        
        adapter = new ImageListFileAdapter(this, imagePathList, bitMapList);
        
        image_list.setAdapter(adapter);
        
        new Thread(new GetImageFilePathThread()).start();
        // loader = new AsyncImageListLoader(this);
        
        // loader.execute(Executors.newCachedThreadPool());
        
        image_list.setOnItemClickListener(this);
        
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (imagePathList.size() > 0) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("filePath", imagePathList.get(position));
            intent.setClass(this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    
    Handler handler = new Handler() {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public void handleMessage(Message msg) {
            HashMap<String, String> map = (HashMap<String, String>) msg.obj;
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                // System.out.println("没有暂停");
                Map.Entry entry = (Map.Entry) it.next();
                String filePath = (String) entry.getKey();
                String bitmapPath = (String) entry.getValue();
                // 把图片添加到适配器里面，以便调整图片的属性
                adapter.addData(filePath, bitmapPath);
                adapter.notifyDataSetChanged();
            }
            map.clear();
            super.handleMessage(msg);
        }
    };
    
    private class GetImageFilePathThread implements Runnable {
        @Override
        public void run() {
            
            File file = new File(Environment.getExternalStorageDirectory() + "");
            
            getFileList(file);
        }
    }
    
    private void getFileList(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    if (".png".equals(getFileEx(f))) {
                        if (!imageMap.containsKey(file.getAbsolutePath())) {
                            imageMap.put(file.getAbsolutePath(), f.getAbsolutePath());
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put(file.getAbsolutePath(), f.getAbsolutePath());
                            Message msg = handler.obtainMessage();
                            msg.obj = temp;
                            handler.sendMessage(msg);
                        }
                    }
                    if (".jpg".equals(getFileEx(f))) {
                        if (!imageMap.containsKey(file.getAbsolutePath())) {
                            imageMap.put(file.getAbsolutePath(), f.getAbsolutePath());
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put(file.getAbsolutePath(), f.getAbsolutePath());
                            Message msg = handler.obtainMessage();
                            msg.obj = temp;
                            handler.sendMessage(msg);
                        }
                    }
                }
                else {
                    getFileList(f);
                }
            }
        }
    }
    
    public String getFileEx(File file) {
        String fileName = file.getName();
        int index = fileName.indexOf('.');
        if (index != -1) {
            int length = fileName.length();
            String str = fileName.substring(index, length);
            return str;
        }
        return "";
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            
            System.out.println("---------------------One------------");
            
        }
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            
            System.out.println("---------------Two------------");
            
        }
    }
    
}
*/