package com.zqh.rxjava.commutilslibrary.localUtils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.zqh.rxjava.commutilslibrary.infor.Gps;
import com.zqh.rxjava.commutilslibrary.utils.SharePreUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class NetWorkLocalUtil {
    private LocationManager locationManager;
    private Context mContext;
    private String latitude;
    private String addr;
    private String longitude;

    public NetWorkLocalUtil(Context mContext) {
        this.mContext = mContext;
        // 获取到LocationManager对象
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }


    public synchronized void getLocation() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    /**
     * 解析地址并显示
     */
    private void localGeocoder(Location currentLocation) {
        Geocoder geoCoder = new Geocoder(mContext, Locale.getDefault());
        try {
            double currentLatitude = currentLocation.getLatitude();
            double currentLongitude = currentLocation.getLongitude();
            List<Address> list = geoCoder.getFromLocation(currentLatitude, currentLongitude,
                    2);
            for (int i = 0; i < list.size(); i++) {
                Address address = list.get(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(address.getCountryName());// 国家
                stringBuilder.append(address.getAdminArea());// 省份
                stringBuilder.append(address.getLocality());// 市
                stringBuilder.append(address.getSubLocality());// 香洲区
                stringBuilder.append(address.getFeatureName());// 周边地址

                currentLatitude = address.getLatitude();
                currentLongitude = address.getLongitude();
                if (CoordinateConvert.outOfChina(currentLatitude, currentLongitude)) {//境外
                    //若是判断为国外坐标则不需要进行转换
                    latitude = String.valueOf(currentLatitude);
                    longitude = String.valueOf(currentLongitude);
                    Log.d("zqh", "out of china");
                } else {//中国境内
                    //国内坐标需要进行地球坐标到百度坐标的转换
                    Gps gps84_To_Gcj022 = CoordinateConvert.gps84_To_Gcj02(currentLatitude, currentLongitude);
                    Gps gcj02_To_Bd09 = CoordinateConvert.gcj02_To_Bd09(gps84_To_Gcj022.getWgLat(), gps84_To_Gcj022.getWgLon());
                    latitude = String.valueOf(gcj02_To_Bd09.getWgLat());//纬度
                    longitude = String.valueOf(gcj02_To_Bd09.getWgLon());//经度
                    Log.d("zqh", "in china");
                }

                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    addr = stringBuilder.toString();
                } else {
                    addr = "定位失败";
                }

                locationManager.removeUpdates(locationListener);
                saveLocation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 创建位置监听器
    private LocationListener locationListener = new LocationListener() {

        // 位置发生改变时调用
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                localGeocoder(location);
            } else {
                locationManager.removeUpdates(locationListener);
            }
        }

        // provider失效时调用
        @Override
        public void onProviderDisabled(String provider) {
        }

        // provider（可用）启用时调用
        @Override
        public void onProviderEnabled(String provider) {
        }

        // 状态改变时调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    /**
     * 存储定位用到的坐标与地址
     */
    private void saveLocation() {

        /********************** 正式用到的坐标以及地址 start **********************/
        SharePreUtil.putString(mContext, "latitude", latitude);
        SharePreUtil.putString(mContext, "longitude", longitude);
        /********************** 正式用到的坐标以及地址 end **********************/
    }
}
