package com.gaolei.mvpmodel.mcontract;

import android.content.Context;

import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mmodel.FeedArticleListData;
import com.gaolei.mvpmodel.base.mmodel.ProjectListData;

/**
 * Created by gaolei on 2018/6/18.
 */

public class HomeContract {

    public interface Presenter  {

        /**
         * Get feed article list
         */
        void getFeedArticleList(int num,Context context);
        void getBannerInfo(Context context);
    }

    public interface View  {

        void showFeedArticleInfo(FeedArticleListData itemBeans);
        void showBannerInfo(BannerListData itemBeans);
    }
}
