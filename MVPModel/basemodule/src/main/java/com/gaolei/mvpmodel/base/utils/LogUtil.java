/**
 * Copyright (C) 2006-2014 Tuniu All rights reserved
 */
package com.gaolei.mvpmodel.base.utils;

import android.util.Log;


public class LogUtil {

    private static boolean IS_PRINTABLE = false;

    public static void init(boolean isPrintable) {
        IS_PRINTABLE = isPrintable;
    }


    public static void v(String tag, String msg) {
        if (!IS_PRINTABLE) {
            return;
        }
        Log.v(tag, msg);
    }


    public static void d(String tag, String msg) {
        if (!IS_PRINTABLE) {
            return;
        }
        Log.d(tag, msg);
    }



    public static void i(String tag, String msg) {
        if (!IS_PRINTABLE) {
            return;
        }
        Log.i(tag, msg);
    }



    public static void w(String tag, String msg) {
        if (!IS_PRINTABLE) {
            return;
        }
        Log.w(tag, msg);
    }


    public static void e(String tag, String msg) {
        if (!IS_PRINTABLE) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void d( String msg) {
        if (!IS_PRINTABLE) {
            return;
        }
        Log.e("gaolei", msg);
    }


}
