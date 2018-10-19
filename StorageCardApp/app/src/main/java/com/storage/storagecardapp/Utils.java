package com.storage.storagecardapp;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.Toast;


import java.security.MessageDigest;

/**
 * Created by gaolei on 2018/6/15.
 */

public class Utils {

    public static void showToast(String content){
        Toast.makeText(BaseApplication.mContext, content, Toast.LENGTH_SHORT).show();
    }

    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo taskInfo = manager.getRunningTasks(1).get(0);
        ComponentName componentInfo = taskInfo.topActivity;
        String shortClassName = componentInfo.getShortClassName();    //类名
//        String className = info.topActivity.getClassName();         //完整类名
//        String packageName = info.topActivity.getPackageName();
        int index=shortClassName.lastIndexOf(".");
        shortClassName=shortClassName.substring(index+1);
        return shortClassName;
    }
}
