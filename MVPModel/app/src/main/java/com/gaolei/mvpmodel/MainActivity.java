package com.gaolei.mvpmodel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.Fragment;
import androidx.core.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import android.view.MenuItem;
import android.widget.Toast;

import com.gaolei.mvpmodel.activity.BaseActivity;
import com.gaolei.mvpmodel.databinding.ActivityMainBinding;
import com.gaolei.mvpmodel.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ArrayList<BaseMvpFragment> mFragments;
    private int mLastFgIndex = 0;
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;

    String[] permissionArray = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    ActivityMainBinding binding;

    @Override
    protected void initData(Bundle bundle) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 取消BottomNavigation大于3个时，动画
        BottomNavigationViewHelper.disableShiftMode(binding.bottomNavigationView);
        mFragments = new ArrayList<BaseMvpFragment>();
        mFragments.add(new HomeFragment());
        mFragments.add(new KnowledgeFragment());
        mFragments.add(new NavigationFragment());
        mFragments.add(new ProjectFragment());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果versionCode>=23 则需要动态授权
            checkPermission();
        } else {
            switchFragment(0);
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        binding.title.setText(getString(R.string.home_pager));
                        switchFragment(0);

                        break;
                    case R.id.tab_knowledge_hierarchy:
                        binding.title.setText(getString(R.string.knowledge_hierarchy));
                        switchFragment(1);

                        break;
                    case R.id.tab_navigation:
                        binding.title.setText(getString(R.string.navigation));
                        switchFragment(2);

                        break;
                    case R.id.tab_project:
                        binding.title.setText(getString(R.string.project));
                        switchFragment(3);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commit();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    public void checkPermission() {
        /**
         * 第 1 步: 检查是否有相应的权限
         */
        boolean isAllGranted = checkPermissionAllGranted(
                permissionArray
        );
        // 如果这权限全都拥有, 则显示HomeFragment
        if (isAllGranted) {
            switchFragment(0);
            return;
        }

        /**
         * 第 2 步: 请求权限
         */
        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(
                this,
                permissionArray,
                MY_PERMISSION_REQUEST_CODE
        );
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    /**
     * 第 3 步: 申请权限结果返回处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;
            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                // 如果所有的权限都授予了, 则显示HomeFragment
                switchFragment(0);
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                getAppDetailSettingIntent(this);
                toast("App正常使用需要授权");

            }
        }
    }

    public void toast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    private void getAppDetailSettingIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(intent);
    }
}
