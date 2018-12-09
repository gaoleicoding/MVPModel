package com.gaolei.mvpmodel.dagger.module;


import com.gaolei.mvpmodel.dagger.scope.PerFragment;
import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;
import com.gaolei.mvpmodel.fragment.UserFragment;

import dagger.Module;
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
public abstract class AllFragmentsModule {
//    @PerFragment
//    @ContributesAndroidInjector(modules = TopicFragmentModule.class)
//    abstract TopicFragment contributeTopicFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHomeFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributeProjectFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract UserFragment contributeMineFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract KnowledgeFragment contributeTreeFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract NavigationFragment contributeNavigationFragmentInjector();


}
