package com.storage.storagecardapp;

import android.app.Application;
import android.content.Context;


import java.io.File;


public class BaseApplication extends Application {
	public static Context mContext;


	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
        initSystemStorage();
	}


    /**
     * 建立存储文件夹.
     */
    public void initSystemStorage() {
        MyStorageManager manager = new MyStorageManager();
        manager.getVolumePaths();
        manager.findDefaultStorage();
        File parentDir = new File(manager.currentPath);
        File dir = new File(manager.currentPath + File.separator
                + ConstantUtils.P_VIDEO);
        try {
            if (!parentDir.exists()) {
                boolean suc = parentDir.mkdirs();
                boolean f = suc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean suc;
        try {
            if (!dir.exists()) {
                suc = dir.mkdirs();
                boolean f = suc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
