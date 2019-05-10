package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gaolei.mvpmodel.mpresenter.BasePresenter;

/**
 * Created by gaolei on 2018/4/26.
 */


public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    public P mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();
        if (mPresenter != null)
            mPresenter.attach( this);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null)
            mPresenter.dettach();
        super.onDestroy();
    }

    //实例presenter
    public abstract P initPresenter();
}
