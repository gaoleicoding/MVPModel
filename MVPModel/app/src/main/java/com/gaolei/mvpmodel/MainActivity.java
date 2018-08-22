package com.gaolei.mvpmodel;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.gaolei.mvpmodel.base.activity.BaseActivity;
import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;
import com.gaolei.mvpmodel.base.utils.PermissionUtil;
import com.gaolei.mvpmodel.view.BottomNavigationViewHelper;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private ArrayList<BaseMvpFragment> mFragments;
    private int mLastFgIndex = 0;

    //    BottomNavigationView bottomNavigationView;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;


    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {


        // 取消BottomNavigation大于3个时，动画
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        mFragments = new ArrayList<BaseMvpFragment>();
        mFragments.add(new HomeFragment());
        mFragments.add(new KnowledgeFragment());
        mFragments.add(new NavigationFragment());
        mFragments.add(new ProjectFragment());
        requestPermission();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        title.setText(getString(R.string.home_pager));
                        switchFragment(0);

                        break;
                    case R.id.tab_knowledge_hierarchy:
                        title.setText(getString(R.string.knowledge_hierarchy));
                        switchFragment(1);

                        break;
                    case R.id.tab_navigation:
                        title.setText(getString(R.string.navigation));
                        switchFragment(2);

                        break;
                    case R.id.tab_project:
                        title.setText(getString(R.string.project));
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



    public void requestPermission(){
        requestPermission(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},new PermissionUtil.RequestPermissionCallBack() {
            @Override
            public void granted() {
//                Toast.makeText(MainActivity.this, "获取权限成功，执行正常操作", Toast.LENGTH_LONG).show();
                switchFragment(0);
            }

            @Override
            public void denied() {
//                Toast.makeText(MainActivity.this, "获取权限失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onRestart(){
        super.onRestart();
        //跳转到设置界面后，重现检查权限
        requestPermission();
    }


}
