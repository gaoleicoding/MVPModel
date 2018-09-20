package com.gaolei.mvpmodel.di.module;

import com.gaolei.mvpmodel.mpresenter.HomePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SampleModule {
    // 关键字，标明该方法提供依赖对象
    @Provides
    HomePresenter providerHomePresenter() {
        //提供Person对象
        return new HomePresenter();
    }
}