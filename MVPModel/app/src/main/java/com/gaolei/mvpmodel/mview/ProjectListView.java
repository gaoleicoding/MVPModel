package com.gaolei.mvpmodel.mview;


import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;

import java.util.List;


public interface ProjectListView extends BaseView {


    void requstProjectList(ProjectListData itemBeans);
    void requstBannerList(BannerListData itemBeans);
}
