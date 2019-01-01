package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.base.mmodel.ArticleListData;
import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;
import com.gaolei.mvpmodel.mcontract.HomeContract;
import com.gaolei.mvpmodel.mpresenter.HomePresenter;


public class UserFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {


    @Override
    public void initData( Bundle bundle) {

    }

    @Override
    public void initView() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void reload() {
    }

//    @Override
//    public BasePresenter initPresenter() {
//        return null;
//    }


    @Override
    protected void loadData() {

    }

    @Override
    public void showArticleList(ArticleListData itemBeans, boolean isRefresh) {

    }

    @Override
    public void showBannerList(BannerListData itemBeans) {

    }
}
