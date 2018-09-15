package com.gaolei.mvpmodel.di.component;

import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.di.module.PresenterModule;
import com.gaolei.mvpmodel.mpresenter.HomePresenter;

import dagger.Component;


/**
 * @author quchao
 * @date 2017/11/27
 */

@Component(modules = PresenterModule.class)
public interface PresenterComponent {


    void inject(BaseMvpFragment fragment);



}
