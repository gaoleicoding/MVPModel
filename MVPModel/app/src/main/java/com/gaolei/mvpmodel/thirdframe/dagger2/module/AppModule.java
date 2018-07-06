package com.gaolei.mvpmodel.thirdframe.dagger2.module;


import com.gaolei.mvpmodel.application.CustomApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * @author quchao
 * @date 2017/11/27
 */

@Module
public class AppModule {

    private final CustomApplication application;

    public AppModule(CustomApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    CustomApplication provideApplicationContext() {
        return application;
    }

//    @Provides
//    @Singleton
//    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
//        return retrofitHelper;
//    }
//
//    @Provides
//    @Singleton
//    DbHelper provideDBHelper(GreenDaoHelper realmHelper) {
//        return realmHelper;
//    }
//
//    @Provides
//    @Singleton
//    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
//        return implPreferencesHelper;
//    }
//
//    @Provides
//    @Singleton
//    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbhelper, PreferenceHelper preferencesHelper) {
//        return new DataManager(httpHelper, dbhelper, preferencesHelper);
//    }

}
