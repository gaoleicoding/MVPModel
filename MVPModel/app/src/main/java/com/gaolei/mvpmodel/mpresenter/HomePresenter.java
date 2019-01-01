package com.gaolei.mvpmodel.mpresenter;


import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mmodel.ArticleListData;
import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.mcontract.HomeContract;
import com.gaolei.mvpmodel.base.thirdframe.rxjava.BaseObserver;

import io.reactivex.Observable;



public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private boolean isRefresh = true;
    private int mCurrentPage = 0;

    @Override
    public void onLoadMore() {
        ++mCurrentPage;
        Observable observable = mRestService.getFeedArticleList(mCurrentPage);
        addSubscribe(observable, new BaseObserver<ArticleListData>(false) {
            @Override
            public void onNext(ArticleListData feedArticleListData) {
                mView.showArticleList(feedArticleListData);
            }
        });
    }

    @Override
    public void getFeedArticleList(int num) {
        Observable observable = mRestService.getFeedArticleList(num);
        addSubscribe(observable, new BaseObserver<ArticleListData>(true) {
            @Override
            public void onNext(ArticleListData feedArticleListData) {
                mView.showArticleList(feedArticleListData);
            }
        });
    }

    @Override
    public void getBannerInfo() {
        Observable observable = mRestService.getBannerListData();
        addSubscribe(observable, new BaseObserver<BannerListData>(true) {

            @Override
            public void onNext(BannerListData bannerListData) {
                mView.showBannerList(bannerListData);
            }

        });


    }

}
