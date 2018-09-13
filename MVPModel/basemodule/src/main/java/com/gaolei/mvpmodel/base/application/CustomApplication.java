package com.gaolei.mvpmodel.base.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Log;


import com.gaolei.basemodule.R;
import com.gaolei.mvpmodel.base.activity.BaseActivity;
import com.gaolei.mvpmodel.base.activity.CustomErrorActivity;
import com.gaolei.mvpmodel.base.utils.LogUtil;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;


public class CustomApplication extends Application {
    public static ConnectivityManager connectivityManager;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        connectivityManager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        context = this;

        LeakCanary.install(this);
        BlockCanary.install(this, new AppContext()).start();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.onEvent(this, "enter", "CustomApplication");//前统计的事件ID
        Class clazz = null;
        try {
            clazz = Class.forName("com.gaolei.mvpmodel.MainActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CustomActivityOnCrash.install(this);
//        CaocConfig.Builder.create()
//                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
//                .enabled(false) //default: true
//                .showErrorDetails(true) //default: true
//                .showRestartButton(true) //default: true
//                .logErrorOnRestart(false) //default: true
//                .trackActivities(true) //default: false
//                .minTimeBetweenCrashesMs(2000) //default: 3000
//                .errorDrawable(R.drawable.customactivityoncrash_error_image) //default: bug image
//                .restartActivity(BaseActivity.class) //default: null (your app's launch activity)
//                .errorActivity(CustomErrorActivity.class) //default: null (default error activity)
//                .eventListener(new CustomEventListener()) //default: null
//                .apply();

    }

    public class AppContext extends BlockCanaryContext {
        private static final String TAG = "AppContext";

        @Override
        public String provideQualifier() {
            String qualifier = "";
            try {
                PackageInfo info = getApplicationContext().getPackageManager()
                        .getPackageInfo(getApplicationContext().getPackageName(), 0);
                qualifier += info.versionCode + "_" + info.versionName + "_YYB";
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "provideQualifier exception", e);
            }
            return qualifier;
        }

        @Override
        public int provideBlockThreshold() {
            return 1000;
        }

        @Override
        public boolean displayNotification() {
            return BuildConfig.DEBUG;
        }

        @Override
        public boolean stopWhenDebugging() {
            return false;
        }
    }

    /**
     * 监听程序崩溃/重启
     */
    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        //程序崩溃回调
        @Override
        public void onLaunchErrorActivity() {
            LogUtil.e("onLaunchErrorActivity()");
        }

        //重启程序时回调
        @Override
        public void onRestartAppFromErrorActivity() {
            LogUtil.e("onRestartAppFromErrorActivity()");
        }

        //在崩溃提示页面关闭程序时回调
        @Override
        public void onCloseAppFromErrorActivity() {
            LogUtil.e("onCloseAppFromErrorActivity()");
        }

    }
}
