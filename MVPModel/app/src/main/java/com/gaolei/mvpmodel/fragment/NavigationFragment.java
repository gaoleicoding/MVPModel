package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;


/**
 * @author quchao
 * @date 2018/2/11
 */

public class NavigationFragment extends BaseMvpFragment {
    @Override
    protected void loadData() {

    }

    @Override
    public void initData( Bundle bundle) {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void reload() {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
