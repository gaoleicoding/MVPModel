package com.gaolei.mvpmodel.thirdframe.dagger2.component;

import android.app.Activity;

import com.gaolei.mvpmodel.MainActivity;
import com.gaolei.mvpmodel.activity.ArticleDetailActivity;
import com.gaolei.mvpmodel.thirdframe.dagger2.module.ActivityModule;
import com.gaolei.mvpmodel.thirdframe.dagger2.scope.ActivityScope;

import dagger.Component;



/**
 * @author quchao
 * @date 2017/11/27
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * 获取Activity实例
     *
     * @return Activity
     */
    Activity getActivity();

    /**
     * 注入MAinActivity所需的依赖
     *
     * @param mainActivity MainActivity
     */
    void inject(MainActivity mainActivity);



}
