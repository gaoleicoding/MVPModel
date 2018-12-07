package com.gaolei.mvpmodel.base.thirdframe.dagger.module;



import dagger.Module;

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
public  abstract class AllFragmentsModule {
//    @PerFragment
//    @ContributesAndroidInjector(modules = TopicFragmentModule.class)
//    abstract TopicFragment contributeTopicFragmentInjector();

    @com.ldlywt.hello.dagger.scope.PerFragment
    @ContributesAndroidInjector()
    abstract HomeFragment contributeHomeFragmentInjector();

    @com.ldlywt.hello.dagger.scope.PerFragment
    @ContributesAndroidInjector()
    abstract MineFragment contributeMineFragmentInjector();

    @com.ldlywt.hello.dagger.scope.PerFragment
    @ContributesAndroidInjector()
    abstract TreeFragment contributeTreeFragmentInjector();

    @com.ldlywt.hello.dagger.scope.PerFragment
    @ContributesAndroidInjector()
    abstract ProjectFragment contributeProjectFragmentInjector();
}
