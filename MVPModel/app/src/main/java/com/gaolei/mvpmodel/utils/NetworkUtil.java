package com.gaolei.mvpmodel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gaolei.mvpmodel.application.CustomApplication;

public class NetworkUtil {
  
    /** 
     * 检查网络是否可用 
     */
    public static boolean isNetworkAvailable() {
  
        ConnectivityManager manager = (ConnectivityManager) CustomApplication.context
                .getApplicationContext().getSystemService(  
                        Context.CONNECTIVITY_SERVICE);  
  
        if (manager == null) {  
            return false;  
        }  
  
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
  
        if (networkinfo == null || !networkinfo.isAvailable()) {  
            return false;  
        }  
  
        return true;  
    }  
      
}  