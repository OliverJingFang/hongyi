package com.cn.businese;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;

import com.cn.hongyi.R;
import com.cn.modle.Person;
import com.cn.util.JSONUtils;
import com.cn.util.JsonService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends InstrumentedActivity implements OnCheckedChangeListener {
    
    private RadioGroup statusRg;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    
    private void initView() {
        statusRg = (RadioGroup) findViewById(R.id.changeOrderRg);
        android.app.FragmentTransaction mTransAction = getFragmentManager().beginTransaction();
        
        OrderFrament mFragment = null;
        if (mFragment == null) {
            mFragment = OrderFrament.newInstance(0);
        }
        mTransAction.replace(R.id.contentlay, mFragment);
        mTransAction.commit();
        statusRg.setOnCheckedChangeListener(this);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(getApplicationContext());
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(getApplicationContext());
        
    }
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        android.app.FragmentTransaction mTransAction = getFragmentManager().beginTransaction();
        
        OrderFrament mFragment = null;
        switch (checkedId) {
            case R.id.status_invalid:
                if (mFragment == null) {
                    mFragment = OrderFrament.newInstance(0);
                }
                mTransAction.replace(R.id.contentlay, mFragment);
                mTransAction.commit();
                break;
            
            case R.id.status_effective:
                if (mFragment == null) {
                    mFragment = OrderFrament.newInstance(1);
                }
                mTransAction.replace(R.id.contentlay, mFragment);
                mTransAction.commit();
                break;
        }
    }
    
    public void initData() {
        Gson gson = new Gson();
        JsonService jsonService = new JsonService();
        Person person = jsonService.getPerson();
        System.out.println("person: " + gson.toJson(person));
        // 对于Object类型，使用 fromJson(String, Class)方法来将Json对象转换成Java对象
        Person person2 = gson.fromJson(gson.toJson(person), Person.class);
        System.out.println(person2);
        System.out.println("------------------------------------------------");
        
        List<Person> persons = jsonService.getPersons();
        System.out.println("persons: " + gson.toJson(persons));
        /*
         * 对于泛型对象，使用fromJson(String, Type)方法来将Json对象转换成对应的泛型对象 new
         * TypeToken<>(){}.getType()方法
         */
        List<Person> persons2 = gson.fromJson(gson.toJson(persons), new TypeToken<List<Person>>() {
        }.getType());
        System.out.println(persons2);
        System.out.println("------------------------------------------------");
        
        List<String> list = jsonService.getString();
        System.out.println("String---->" + gson.toJson(list));
        List<String> list2 = gson.fromJson(gson.toJson(list), new TypeToken<List<String>>() {
        }.getType());
        System.out.println("list2---->" + list2);
        System.out.println("------------------------------------------------");
        
        List<Map<String, String>> listMap = jsonService.getMapList();
        System.out.println("Map---->" + gson.toJson(listMap));
        List<Map<String, String>> listMap2 = gson.fromJson(gson.toJson(listMap),
                                                           new TypeToken<List<Map<String, String>>>() {
                                                           }.getType());
        System.out.println("listMap2---->" + listMap2);
        System.out.println("------------------------------------------------");
        
        String yunyang = JSONUtils.getCityCodeByOrgJson(MainActivity.this, "北京", "朝阳");
        String chegndu = JSONUtils.getCityCodeByOrgJson(MainActivity.this, "四川", "成都");
        Log.e("HY", "yunyang" + yunyang);
        Log.e("HY", "chegndu" + chegndu);
        String chaoyang = JSONUtils.getCityCodeByFastJson(MainActivity.this, "北京", "朝阳");
        Log.e("HY", "chaoyang" + chaoyang);
    }
    
}
