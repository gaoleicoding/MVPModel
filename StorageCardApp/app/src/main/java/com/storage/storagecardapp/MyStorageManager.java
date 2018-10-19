package com.storage.storagecardapp;

import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用来获取存储路径的类.
 *
 * @author Yan
 */
public class MyStorageManager {
    private StorageManager sManager;
    private Method methodGetPaths;
    public static String currentPath;
    private String[] pathsTmp;
    public static ArrayList<String> paths;
    public static HashMap<String, Boolean> pathsAvailable;

    public MyStorageManager() {
        sManager = (StorageManager) BaseApplication.mContext
                .getSystemService(BaseApplication.mContext.STORAGE_SERVICE);
        paths = new ArrayList<String>();
        pathsAvailable = new HashMap<String, Boolean>();
        try {
            methodGetPaths = sManager.getClass().getMethod("getVolumePaths");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            methodGetPaths = null;
        }
    }

    /**
     * 获取可用的存储路径.
     */
    public void getVolumePaths() {
        boolean suc = false;
        try {

            if (android.os.Build.VERSION.SDK_INT >= 19) {
                pathsTmp = getExtraPath();
            } else {
                pathsTmp = (String[]) methodGetPaths.invoke(sManager);
            }
            suc = true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!suc) { // 反射失败，使用系统默认的存储路径，4.4以前用返回外置存储卡，4.4之后会返回内置存储路径.
                if (SDUtils.checkSDExit()) {
                    pathsTmp = new String[1];
                    pathsTmp[0] = Environment.getExternalStorageDirectory()
                            .getAbsolutePath();
                } else {// currentPath = pathsTmp[0];
                    pathsTmp = new String[1];
                    pathsTmp[0] = "";
                }
            }
            checkStorage();
        }

    }

    public String[] getExtraPath() {
        String extra = BaseApplication.mContext.getExternalFilesDir(null)
                .getAbsolutePath();
        List<String> path = new ArrayList<String>();
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            File[] files = ContextCompat.getExternalFilesDirs(
                    BaseApplication.mContext, null);

            for (File file : files) {
                if (file != null) {
                    String tmp = file.getAbsolutePath();

                    if (!tmp.equals(extra) && !tmp.contains(extra)) {
                        path.add(tmp);
                    } else {
                        String p = tmp.substring(0,
                                tmp.indexOf("/Android/data"));
                        path.add(p);
                    }

                }
            }
        }
        String[] result = new String[path.size()];
        for (int i = 0; i < path.size(); i++) {
            result[i] = path.get(i);
        }
        return result;
    }

    public void checkStorage() {
        for (String path : pathsTmp) {
            if (SDUtils.checkZeroFile(path)) {
                paths.add(path);
            }
        }
    }

    /**
     * get the storage for Download video. init when application is created.
     *
     * @return the stroage dir path.
     */
    public void findDefaultStorage() {

        currentPath = PreferenceUtils.getPrefString(BaseApplication.mContext, ConstantUtils.KEY_DOWNLOCATION, "");
        if (currentPath.equals("")) {
            currentPath = getMostAvailable();
            PreferenceUtils.setPrefString(BaseApplication.mContext, ConstantUtils.KEY_DOWNLOCATION, currentPath);
        }
        boolean hasOtherAvailable = false;
        boolean oldAvailable = false;
        String strTmpPath = "";
        for (String s : paths) {
            boolean thisAvailable = isValidateLocation(s);
            if (thisAvailable) {
                pathsAvailable.put(s, thisAvailable);
                strTmpPath = s;
                hasOtherAvailable = true;
            }
            if (s.equals(currentPath)) { //检测原来的存储位置是否可用.
                if (thisAvailable) {
                    oldAvailable = true;
                } else {
                    //原来的存储位置不可用.
                }
            }
        }
        //原来位置不可用
        if (!oldAvailable) {
            if (hasOtherAvailable) {
                //有其他位置可用
                currentPath = strTmpPath;
                PreferenceUtils.setPrefString(BaseApplication.mContext, ConstantUtils.KEY_DOWNLOCATION, currentPath);
            } else {//没有可用位置.
                currentPath = getMostAvailable();
                PreferenceUtils.setPrefString(BaseApplication.mContext, ConstantUtils.KEY_DOWNLOCATION, currentPath);
            }
        }

    }

    /**
     * 获取可用空间最大的存储路径.
     *
     * @return
     */
    public String getMostAvailable() {
        String tmp = "";
        long size = 0;
        for (String path : paths) {
            long tmpSize = SDUtils.getAvailableSize(path);
            if (tmpSize >= size) {
                size = tmpSize;
                tmp = path;
            }
        }
        return tmp;
    }

    private boolean isValidateLocation(String path) {
        boolean canUse = false;
        try {
            File dir = new File(path);
            File childDir = new File(path
                    + File.separator + ConstantUtils.P_VIDEO);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!childDir.exists()) {
                canUse = childDir.mkdirs();
            } else {
                canUse = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return canUse;
    }


}
