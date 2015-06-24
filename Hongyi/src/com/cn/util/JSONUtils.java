package com.cn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.cn.hongyi.R;
import com.cn.modle.City;
import com.cn.modle.Province;

public class JSONUtils {
    
    public String getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", "hongyi");
            obj.optString("name");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 根据城市和省去拿城市编码 官方json org.json
     * 
     * @param mconContext
     * @param provinceName
     * @param cityName
     * @return
     */
    public static String getCityCodeByOrgJson(Context mContext, String provinceName, String cityName) {
        String JsonString = mContext.getResources().getString(R.string.citycode);
        String result = "";
        try {
            JSONObject mJb = new JSONObject(JsonString);
            JSONArray mArray = mJb.getJSONArray(("城市代码"));
            for (int i = 0, size = mArray.length(); i < size; i++) {
                result = mArray.getString(i);
                JSONObject pJb = new JSONObject(result);
                String province = pJb.optString("省");
                if (provinceName.equalsIgnoreCase(province)) {
                    JSONArray mVince = pJb.getJSONArray("市");
                    for (int j = 0, length = mVince.length(); j < length; j++) {
                        JSONObject mJb1 = new JSONObject(mVince.getString(j));
                        if (mJb1.opt("市名").equals(cityName)) {
                            result = mJb1.optString("编码");
                            return result;
                        }
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String getCityCodeByFastJson(Context mContext, String provinceName, String cityName) {
        String JsonString = mContext.getResources().getString(R.string.citycode);
        String result = "";
        try {
            com.alibaba.fastjson.JSONObject mObj = JSON.parseObject(JsonString);
            com.alibaba.fastjson.JSONArray fArray = mObj.getJSONArray("城市代码");
            ArrayList<Province> proList = new ArrayList<Province>();
            
            for (int i = 0; i < fArray.size(); i++) {
                Province mProvince = new Province();
                ArrayList<City> cList = new ArrayList<City>();
                mProvince.cityList = cList;
                com.alibaba.fastjson.JSONObject mObj1 = JSON.parseObject(fArray.get(i).toString());
                com.alibaba.fastjson.JSONArray fArray1 = mObj1.getJSONArray("市");
                String mS = mObj1.getString("省");
                ArrayList<City> mCityList = new ArrayList<City>();
                for (int j = 0, size = fArray1.size(); j < size; j++) {
                    mCityList.add(fArray1.getObject(j, City.class));
                }
                mProvince.cityList = mCityList;
                mProvince.省 = mS;
                proList.add(mProvince);
            }
            for (int j = 0, size = proList.size(); j < size; j++) {
                if (provinceName.equalsIgnoreCase(proList.get(j).省)) {
                    for (int z = 0, length = proList.get(j).cityList.size(); z < length; z++) {
                        if (proList.get(j).cityList.get(z).市名.equals(cityName)) {
                            result = proList.get(j).cityList.get(z).编码;
                            return result;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * @param citiesString
     *            从服务器端得到的JSON字符串数据
     * @return 解析JSON字符串数据，放入List当中
     */
    public static List<String> parseCities(String citiesString) {
        List<String> cities = new ArrayList<String>();
        
        try {
            JSONObject jsonObject = new JSONObject(citiesString);
            JSONArray jsonArray = jsonObject.getJSONArray("cities");
            for (int i = 0; i < jsonArray.length(); i++) {
                cities.add(jsonArray.getString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
    
    public static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the
         * BufferedReader.readLine() method. We iterate until the BufferedReader
         * return null which means there's no more data to read. Each line will
         * appended to a StringBuilder and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return sb.toString();
    }
    
}
