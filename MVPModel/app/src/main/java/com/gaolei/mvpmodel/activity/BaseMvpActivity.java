package com.gaolei.mvpmodel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.thirdframe.dagger2.component.ActivityComponent;
import com.gaolei.mvpmodel.thirdframe.dagger2.component.DaggerActivityComponent;
import com.gaolei.mvpmodel.thirdframe.dagger2.module.ActivityModule;

import javax.inject.Inject;


/**
 * Created by liuhaiyang on 2017/8/2.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    @Inject
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPresenter = initPresenter();

        mPresenter.attach( this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(CustomApplication.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettach();
        super.onDestroy();
    }

//    //实例presenter
//    public abstract P initPresenter();
    /**
     * 注入当前Activity所需的依赖
     */
    protected abstract void initInject();
}
