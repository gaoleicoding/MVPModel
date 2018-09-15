package com.gaolei.mvpmodel;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.gaolei.mvpmodel.adapter.MainTabAdapter;
import com.gaolei.mvpmodel.base.activity.BaseActivity;
import com.gaolei.mvpmodel.base.utils.LogUtil;
import com.gaolei.mvpmodel.base.utils.PermissionUtil;
import com.gaolei.mvpmodel.base.utils.Utils;
import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;
import com.gaolei.mvpmodel.fragment.UserFragment;
import com.gaolei.mvpmodel.view.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> titles;

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.title)
    TextView title;
    HomeFragment homeFragment;
    ProjectFragment projectFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        initView();
        requestPermission();

    }
    protected void initView(){
        mFragments = new ArrayList<Fragment>();
        homeFragment = new HomeFragment();
        projectFragment = new ProjectFragment();
        mFragments.add(homeFragment);
        mFragments.add(projectFragment);
        mFragments.add(new KnowledgeFragment());
        mFragments.add(new NavigationFragment());
        mFragments.add(new UserFragment());

        titles = new ArrayList<String>();
        titles.add(getString(R.string.home));
        titles.add(getString(R.string.project));
        titles.add(getString(R.string.knowledge));
        titles.add(getString(R.string.navigation));
        titles.add(getString(R.string.mine));

        MainTabAdapter adapter = new MainTabAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);
        initTab();
    }


    /**
     * 设置添加Tab
     */
    private void initTab() {

        tabLayout.getTabAt(0).setCustomView(R.layout.tab_home);
        tabLayout.getTabAt(1).setCustomView(R.layout.tab_project);
        tabLayout.getTabAt(2).setCustomView(R.layout.tab_knowledge);
        tabLayout.getTabAt(3).setCustomView(R.layout.tab_navigation);
        tabLayout.getTabAt(4).setCustomView(R.layout.tab_mine);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //标签选中之后执行的方法
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                title.setText(titles.get(tab.getPosition()));
//                if(tab.getPosition()==1)
//                    throw new RuntimeException("I'm a cool exception and I crashed the main thread!");

            }

            //标签没选中
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //默认选中的Tab
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    @OnClick({R.id.title})
    public void onClick() {
        int index = viewPager.getCurrentItem();
        if (index == 0)
            homeFragment.scrollToTop();
        if (index == 1)
            projectFragment.scrollToTop();


    }

    public void requestPermission() {
        requestPermission(this, new PermissionUtil.RequestPermissionCallBack() {

            @Override
            public void granted() {

            }

            @Override
            public void denied() {
            }
        }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE});
    }

    public void onRestart() {
        super.onRestart();
        //跳转到设置界面后返回，重新检查权限
        requestPermission();
    }
}
