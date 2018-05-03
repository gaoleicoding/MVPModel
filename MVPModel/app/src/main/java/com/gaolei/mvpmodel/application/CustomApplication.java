package com.gaolei.mvpmodel.application;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.gaolei.mvpmodel.R;


public class CustomApplication extends Application {

    public static RequestOptions options;
    @Override
    public void onCreate() {
        super.onCreate();
       options = new RequestOptions()
//                .placeholder(R.drawable.ic_launcher)// 正在加载中的图片
//                .error(R.drawable.video_error) // 加载失败的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 磁盘缓存策略
    }
    }
