package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.mpresenter.BasePresenter;



public class ProjectFragment extends BaseMvpFragment {


    @Override
    public void initData( Bundle bundle) {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void reload() {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
