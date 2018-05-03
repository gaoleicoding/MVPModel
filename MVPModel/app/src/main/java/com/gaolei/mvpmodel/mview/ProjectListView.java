package com.gaolei.mvpmodel.mview;


import com.gaolei.mvpmodel.mmodel.FeedArticleData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;

import java.util.List;

/**
 * 倒计时mview
 * Created by caiwancheng on 2017/8/29.
 */

public interface ProjectListView extends BaseView {


    void requstProjectList(ProjectListData itemBeans);
}
