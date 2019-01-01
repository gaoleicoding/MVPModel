package com.gaolei.mvpmodel.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;




public abstract class BaseMvpActivity< P extends BasePresenter> extends BaseActivity {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettach();
        super.onDestroy();
    }

    //实例presenter
    public abstract P initPresenter();
}
