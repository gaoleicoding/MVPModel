package com.gaolei.mvpmodel.dagger.component;


import com.gaolei.mvpmodel.application.App;
import com.gaolei.mvpmodel.dagger.module.AllActivitysMoudle;
import com.gaolei.mvpmodel.dagger.module.AllFragmentsModule;


import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AllActivitysMoudle.class,
        AllFragmentsModule.class
})
public interface AppComponent {
    void inject(App app);
}
