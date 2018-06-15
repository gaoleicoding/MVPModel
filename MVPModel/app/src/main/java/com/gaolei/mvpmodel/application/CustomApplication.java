package com.gaolei.mvpmodel.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.gaolei.mvpmodel.R;


public class CustomApplication extends Application {
    public static ConnectivityManager connectivityManager;
    public static RequestOptions options;
    @Override
    public void onCreate() {
        super.onCreate();
       options = new RequestOptions()
//                .placeholder(R.drawable.ic_launcher)// 正在加载中的图片
//                .error(R.drawable.video_error) // 加载失败的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 磁盘缓存策略
        connectivityManager= (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public static boolean isNetworkAvalible() {
        // 获得网络状态管理器

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    }
