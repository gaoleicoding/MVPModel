package com.storage.storagecardapp;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BasePermisssionActivity {

    private RelativeLayout rltDownloadPosition;
    private TextView text_setting_storage_position;
    private String defaultPath = ""; // 默认的存储路径.
    private HashMap<String, String> pathList; // = new ArrayList<String>();
    private int pathSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rltDownloadPosition = findViewById(R.id.rlt_setting_download_position);
        text_setting_storage_position =  findViewById(R.id.text_setting_storage_position);

        requestPermission();
        setStorageLocation();
        if (PreferenceUtils.getPrefInt(this, ConstantUtils.KEY_SP_PATH, ConstantUtils.PATH_VIDEO_MEMORY) ==
                ConstantUtils.PATH_VIDEO_MEMORY) {
            text_setting_storage_position.setText(getResources().getString(
                    R.string.text_memory_card));
        } else {
            text_setting_storage_position.setText(getResources().getString(
                    R.string.text_sd_card));

        }
        rltDownloadPosition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StorageVideoDialog dialog = new StorageVideoDialog(
                        MainActivity.this, R.style.DefaultDialog);

                dialog.setDialogTitle(getResources().getString(
                        R.string.text_position_storage_video));


                dialog.setOnInfoCallback(new StorageVideoDialog.InfoCallback() {
                    String path = "";
                    @Override
                    public void onSelected(int position) {
                        if (position == ConstantUtils.PATH_VIDEO_MEMORY) {
                            path = pathList.get("inner");
                            text_setting_storage_position.setText(getString(R.string.text_memory_card));

                        }
                        if (position == ConstantUtils.PATH_VIDEO_SD) {
                            path = pathList.get("out");
                            text_setting_storage_position.setText(getString(R.string.text_sd_card));

                        }
                        isValidateLocation(path, false);
                        setStorageLocation();
                    }

                });
                dialog.show();
            }
        });
    }
    public void requestPermission() {
        requestPermission(MainActivity.this, new PermissionUtil.RequestPermissionCallBack() {

            @Override
            public void granted() {
            }

            @Override
            public void denied() {
            }
        }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }

    public void onRestart() {
        super.onRestart();
        //跳转到设置界面后返回，重新检查权限
        requestPermission();
    }

    /**
     * 尝试切换存储路径.
     *
     * @param path
     * @return
     */
    private boolean isValidateLocation(String path, boolean isOnlyCheck) {
        if (TextUtils.isEmpty(path)) {
            // path为空
            if (!isOnlyCheck)
                Toast.makeText(MainActivity.this, R.string.bad_storage,
                        Toast.LENGTH_SHORT).show();
            //
            //
            return false;
        }
        if (!SDUtils.checkEmptySize(path)) {
            // 存储卡size不够大的情况
            if (!isOnlyCheck)
                Toast.makeText(MainActivity.this, R.string.full_storage,
                        Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean canUse = false;
        try {
            File dir = new File(MyStorageManager.currentPath);
            File childDir = new File(MyStorageManager.currentPath
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

        if (canUse) {
            if (!isOnlyCheck) {
                PreferenceUtils.setPrefString(this, ConstantUtils.KEY_DOWNLOCATION, path);
                MyStorageManager.currentPath = path;
            }
        } else {
            if (!isOnlyCheck)
                Toast.makeText(MainActivity.this, R.string.bad_storage,
                        Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /**
     * 选择识别存储路径的私有方法.
     */

    private void setStorageLocation() {
        File file = Environment.getExternalStorageDirectory();
        if (file != null) {
            defaultPath = file.getAbsolutePath();
        }
        ArrayList<String> paths = MyStorageManager.paths;

        pathList = new HashMap<String, String>();

        for (int i = 0; i < paths.size(); i++) {
            String name = getPathName(paths.get(i), getAvailablePathNumber() <= 1);
            pathList.put(name, paths.get(i));
            if (name.equals("inner")
                    && paths.get(i).equals(MyStorageManager.currentPath)) {
                // 内置
                // rdgPosition.check(R.id.rbt_download_position_memory);
                pathSave = ConstantUtils.PATH_VIDEO_MEMORY;
                PreferenceUtils.setPrefInt(MainActivity.this, ConstantUtils.KEY_SP_PATH, pathSave);

            }
            if (name.equals("out")
                    && paths.get(i).equals(MyStorageManager.currentPath)) {
                // rdgPosition.check(R.id.rbt_download_position_sd);
                pathSave = ConstantUtils.PATH_VIDEO_SD;
                PreferenceUtils.setPrefInt(MainActivity.this,  ConstantUtils.KEY_SP_PATH, pathSave);
            }
        }

    }
    /**
     * 识别存储卡是内置还是外置的方法.
     *
     * @param path
     * @param only 是不是唯一的存储卡
     * @return
     */
    private String getPathName(String path, boolean only) {
        if (defaultPath.length() > 0) {
            if (defaultPath.equals(path) || defaultPath.contains(path)) {
                if (android.os.Build.VERSION.SDK_INT >= 19 || only) {
                    return "inner";
                } else {
                    return "out";
                }
            } else {
                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    return "out";
                } else {
                    return "inner";
                }
            }
        } else {
            return "inner";
        }
    }
    private int getAvailablePathNumber() {
        return MyStorageManager.pathsAvailable.size();
    }
}
