package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.thirdframe.dagger2.component.FragmentComponent;
import com.gaolei.mvpmodel.thirdframe.dagger2.module.FragmentModule;
import com.gaolei.mvpmodel.thirdframe.dagge2.component.DaggerFragmentComponent;

import javax.inject.Inject;

/**
 * Created by gaolei on 2018/4/26.
 */


public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    @Inject
    public P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        super.onCreate(savedInstanceState);

//        mPresenter = initPresenter();
        if (mPresenter != null)
            mPresenter.attach( this);
    }
    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(CustomApplication.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }
    @Override
    public void onDestroy() {
        if (mPresenter != null)
            mPresenter.dettach();
        super.onDestroy();
    }

//    //实例presenter
//    public abstract P initPresenter();



    /**
     * 注入当前Fragment所需的依赖
     */
    protected abstract void initInject();
}
