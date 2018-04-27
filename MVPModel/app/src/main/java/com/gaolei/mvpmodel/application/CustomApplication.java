package com.gaolei.mvpmodel.application;

import android.app.Application;
import android.content.Context;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.weiyankeji.fujindai.utils.ConstantUtils;


public class CustomApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    }
