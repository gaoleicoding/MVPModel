package com.gaolei.mvpmodel.activity;

import android.os.Bundle;

import com.gaolei.mvpmodel.mpresenter.BasePresenter;

import androidx.annotation.Nullable;


/**
 * Created by liuhaiyang on 2017/8/2.
 */

public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends BaseActivity {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettach();
        super.onDestroy();
    }

    //实例presenter
    public abstract P initPresenter();
}
