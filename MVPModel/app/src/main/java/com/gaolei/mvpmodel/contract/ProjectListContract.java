package com.gaolei.mvpmodel.contract;

import android.content.Context;

import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;

/**
 * Created by gaolei on 2018/6/18.
 */

public class ProjectListContract {

    public interface Presenter  {

        void getProjectInfo(int page, int cid, Context context);
        void getBannerInfo(Context context);
    }

    public interface View  {

        void showProjectInfo(ProjectListData itemBeans);
        void showBannerInfo(BannerListData itemBeans);
    }
}
