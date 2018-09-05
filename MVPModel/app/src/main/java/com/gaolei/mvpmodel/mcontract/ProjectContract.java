package com.gaolei.mvpmodel.mcontract;

import android.content.Context;

import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mmodel.ProjectListData;

/**
 * Created by gaolei on 2018/6/18.
 */

public class ProjectContract {

    public interface Presenter  {

        void getProjectInfo(int page, int cid, Context context);
    }

    public interface View  {

        void showProjectInfo(ProjectListData itemBeans);
    }
}
