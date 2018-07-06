package com.gaolei.mvpmodel.thirdframe.dagger2.component;


import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.thirdframe.dagger2.module.AppModule;
import com.gaolei.mvpmodel.thirdframe.dagger2.module.HttpModule;
import com.gaolei.mvpmodel.thirdframe.retrofit.RestApi;

import javax.inject.Singleton;

import dagger.Component;


/**
 * @author quchao
 * @date 2017/11/27
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    CustomApplication getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    RestApi getRestApi();

}
