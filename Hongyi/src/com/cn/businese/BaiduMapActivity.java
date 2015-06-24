package com.cn.businese;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.cn.hongyi.R;
import com.cn.util.message.ToastUtil;

public class BaiduMapActivity extends Activity implements OnClickListener {
    
    private MapView mMapView;
    
    private BaiduMap mBaiduMap;
    
    private Button showMyPositionBt;
    
    private LocationClient mLocationClient;
    
    private LocationMode tempMode = LocationMode.Hight_Accuracy;
    
    private String tempcoor = "gcj02";
    
    private BitmapDescriptor mCurrentMarker;
    
    public MyLocationListenner myListener = new MyLocationListenner();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        showMyPositionBt = (Button) findViewById(R.id.show_my_position);
        showMyPositionBt.setOnClickListener(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        initLocation();
    }
    
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);// ���ö�λģʽ
        option.setOpenGps(true);
        option.setCoorType(tempcoor);// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
        mLocationClient.setLocOption(option);
        
        LatLng point = new LatLng(39.963175, 116.400244);
        // ����Markerͼ��
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon);
        // ����MarkerOption�������ڵ�ͼ�����Marker
        OverlayOptions option1 = new MarkerOptions().position(point).icon(bitmap);
        // �ڵ�ͼ�����Marker������ʾ
        mBaiduMap.addOverlay(option1);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(point);
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        // activity ��ͣʱͬʱ��ͣ��ͼ�ؼ�
        mMapView.onPause();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // activity �ָ�ʱͬʱ�ָ���ͼ�ؼ�
        mMapView.onResume();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity ����ʱͬʱ���ٵ�ͼ�ؼ�
        mMapView.onDestroy();
    }
    
    @Override
    public void onClick(View v) {
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        mLocationClient.requestLocation();
    }
    
    /**
     * ��λSDK��������
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e("FJ", "11�յ���location�ż���");
            // map view ���ٺ��ڴ����½��յ�λ��
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
                mBaiduMap.setMyLocationData(locData);
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
        }

        public void onReceivePoi(BDLocation poiLocation) {
            Log.e("FJ", "22222222222");
        }
    }
    
}
