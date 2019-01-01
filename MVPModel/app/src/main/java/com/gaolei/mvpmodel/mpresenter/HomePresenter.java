package com.gaolei.mvpmodel.mpresenter;


import android.content.Context;

import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mview.ProjectListView;
import com.gaolei.mvpmodel.rxjava.BaseObserver;

import io.reactivex.Observable;

public class HomePresenter extends BasePresenter<ProjectListView> {

    public void getProjectInfo(int page, int cid, Context context) {
        Observable observable = mRestService.getProjectListData(page, cid);
        doSubscribe(observable,new BaseObserver<ProjectListData>(context) {
            @Override
            public void onNext(ProjectListData projectListData) {
                mView.requstProjectList(projectListData);
            }
        });
    }
    public void getBannerInfo(Context context) {
        Observable observable = mRestService.getBannerListData();
        doSubscribe(observable,new BaseObserver<BannerListData>(context) {

            @Override
            public void onNext(BannerListData bannerListData) {
                mView.requstBannerList(bannerListData);
            }

        });


    }

}
