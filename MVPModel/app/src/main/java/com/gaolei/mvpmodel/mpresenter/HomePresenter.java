package com.gaolei.mvpmodel.mpresenter;


import android.content.Context;

import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mmodel.FeedArticleListData;
import com.gaolei.mvpmodel.base.mmodel.ProjectListData;
import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.mcontract.HomeContract;
import com.gaolei.mvpmodel.thirdframe.rxjava.BaseObserver;

import io.reactivex.Observable;

/**
 * Created by caiwancheng on 2017/8/30.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{


    @Override
    public void getFeedArticleList(int num,Context context) {
                Observable observable = mRestService.getFeedArticleList(num);
        doSubscribe(observable,new BaseObserver<FeedArticleListData>(context){
            @Override
            public void onNext(FeedArticleListData feedArticleListData) {
                mView.showFeedArticleInfo(feedArticleListData);
            }
        });
    }

    @Override
    public void getBannerInfo(Context context) {
        Observable observable = mRestService.getBannerListData();
        doSubscribe(observable,new BaseObserver<BannerListData>(context) {

            @Override
            public void onNext(BannerListData bannerListData) {
                mView.showBannerInfo(bannerListData);
            }

        });


    }

}
