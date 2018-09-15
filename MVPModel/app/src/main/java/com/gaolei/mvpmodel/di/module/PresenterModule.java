package com.gaolei.mvpmodel.di.module;

import android.support.v4.app.Fragment;

import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.mcontract.ProjectContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    BasePresenter basePresenter;

    public PresenterModule(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    // 关键字，标明该方法提供依赖对象
    @Provides
    BasePresenter providerPresenter() {
        //提供Person对象
        return basePresenter;
    }
}