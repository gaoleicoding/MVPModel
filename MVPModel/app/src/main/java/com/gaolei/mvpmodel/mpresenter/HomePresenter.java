package com.gaolei.mvpmodel.mpresenter;


import android.util.Log;

import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mview.ProjectListView;
import com.gaolei.mvpmodel.net.RestService;
import com.gaolei.mvpmodel.net.UrlConfig;
import com.gaolei.mvpmodel.rxjava.BaseObserver;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caiwancheng on 2017/8/30.
 */

public class HomePresenter extends BasePresenter<ProjectListView> {

    public void getProjectInfo(int page, int cid) {
        mView.showLoading();
        Observable observable = mRestService.getProjectListData(page, cid);
        doSubscribe(observable,new BaseObserver<ProjectListData>(mView) {
            @Override
            public void onNext(ProjectListData projectListData) {
                mView.requstProjectList(projectListData);
                mView.hideLoading();
            }
        });
    }
    public void getBannerInfo() {
        Observable observable = mRestService.getBannerListData();
        doSubscribe(observable,new BaseObserver<BannerListData>(mView) {

            @Override
            public void onNext(BannerListData bannerListData) {
                mView.requstBannerList(bannerListData);
                mView.hideLoading();
            }

        });


    }

}
