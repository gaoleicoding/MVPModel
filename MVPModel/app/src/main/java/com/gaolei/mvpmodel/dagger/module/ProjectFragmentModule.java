package com.gaolei.mvpmodel.dagger.module;


import com.gaolei.mvpmodel.mpresenter.HomePresenter;
import com.gaolei.mvpmodel.mpresenter.ProjectPresenter;

import dagger.Module;
import dagger.Provides;

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
public abstract class ProjectFragmentModule {
    @Provides
    static ProjectPresenter provideProjectPresenter() {
        return new ProjectPresenter();
    }
}
