package com.gaolei.mvpmodel.mcontract;

import com.gaolei.mvpmodel.base.mmodel.ProjectListData;

/**
 * Created by gaolei on 2018/6/18.
 */

public class ProjectContract {

    public interface Presenter {

        void getProjectInfo(int page, int cid);

        void onLoadMore(int cid);
    }

    public interface View {

        void showProjectList(ProjectListData itemBeans, boolean isRefresh);
    }
}
