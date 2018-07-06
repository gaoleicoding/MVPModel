package com.gaolei.mvpmodel.thirdframe.dagger2.component;

import android.app.Activity;

import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;
import com.gaolei.mvpmodel.thirdframe.dagger2.module.FragmentModule;
import com.gaolei.mvpmodel.thirdframe.dagger2.scope.FragmentScope;

import dagger.Component;


/**
 * @author quchao
 * @date 2017/11/27
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    /**
     * 获取Activity实例
     *
     * @return Activity
     */
    Activity getActivity();

    void inject(HomeFragment homeFragment);

    void inject(KnowledgeFragment knowledgeFragment);

    void inject(ProjectFragment projectFragment);

    void inject(NavigationFragment navigationFragment);

}
