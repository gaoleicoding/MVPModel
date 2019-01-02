package com.gaolei.mvpmodel.mpresenter;


import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mmodel.ArticleListData;
import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.mcontract.HomeContract;
import com.gaolei.mvpmodel.base.thirdframe.rxjava.BaseObserver;

import io.reactivex.Observable;


public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private int mCurrentPage = 0;

    //加载更多
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

    //获取文章列表数据
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

    //获取banner数据
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
