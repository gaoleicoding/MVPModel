/**
 * Copyright (C) 2006-2014 Tuniu All rights reserved
 */
package com.gaolei.mvpmodel.base.utils;

import android.util.Log;

import com.gaolei.basemodule.BuildConfig;


public class LogUtil {

    private static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "zmy";

    public static void init(boolean isPrintable) {
        isDebug = isPrintable;
    }

    public static void d(String msg) {
        if (!isDebug) {
            return;
        }
        Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (!isDebug) {
            return;
        }
        Log.v(TAG, msg);
    }


    public static void i(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        Log.i(tag, msg);
    }


    public static void w(String msg) {
        if (!isDebug) {
            return;
        }
        Log.w(TAG, msg);
    }


    public static void e(String msg) {
        if (!isDebug) {
            return;
        }
        Log.e(TAG, msg);
    }


}
