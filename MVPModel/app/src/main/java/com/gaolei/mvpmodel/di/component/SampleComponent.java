package com.gaolei.mvpmodel.di.component;

import com.gaolei.mvpmodel.di.module.SampleModule;
import com.gaolei.mvpmodel.fragment.HomeFragment;

import dagger.Component;

@Component(modules = SampleModule.class)
public interface SampleComponent {

    void inject(HomeFragment homeFragment);

}