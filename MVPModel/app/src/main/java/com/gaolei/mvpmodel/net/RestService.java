package com.gaolei.mvpmodel.net;


import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RestService {

/*
    http://www.wanandroid.com/project/list/1/json?cid=294
    方法：GET
    参数：cid 分类的id，页码：拼接在链接中，从1开始。
    */
    @GET("project/list/{page}/json")
    Observable<ProjectListData> getProjectListData(@Path("page") int page, @Query("cid") int cid);
/*
    http://www.wanandroid.com/banner/json
    广告栏数据
*/

    @GET("banner/json")
    Observable<BannerListData> getBannerListData();
}
