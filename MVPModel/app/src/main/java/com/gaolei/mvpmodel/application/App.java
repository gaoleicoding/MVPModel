package com.gaolei.mvpmodel.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.gaolei.mvpmodel.base.application.CustomApplication;
import com.gaolei.mvpmodel.base.utils.LogUtil;

import com.gaolei.mvpmodel.dagger.component.DaggerAppComponent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import javax.inject.Inject;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class App extends CustomApplication implements HasSupportFragmentInjector, HasActivityInjector {

    public static ConnectivityManager connectivityManager;
    public static Context context;
    @Inject
    DispatchingAndroidInjector<Activity> mDispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.create().inject(this);



    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }



}
