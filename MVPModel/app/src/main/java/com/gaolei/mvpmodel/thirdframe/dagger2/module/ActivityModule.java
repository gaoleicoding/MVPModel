package com.gaolei.mvpmodel.thirdframe.dagger2.module;

import android.app.Activity;

import com.gaolei.mvpmodel.thirdframe.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return mActivity;
    }

}
