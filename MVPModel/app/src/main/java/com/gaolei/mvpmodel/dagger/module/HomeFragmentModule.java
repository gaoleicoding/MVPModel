package com.gaolei.mvpmodel.dagger.module;


import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;
import com.gaolei.mvpmodel.fragment.UserFragment;
import com.gaolei.mvpmodel.mpresenter.HomePresenter;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * <pre>
 *     author : lex
 *     e-mail : ldlywt@163.com
 *     time   : 2018/09/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Module
public abstract class HomeFragmentModule {
    @Provides
    static HomePresenter provideHomePresenter() {
        return new HomePresenter();
    }
}
