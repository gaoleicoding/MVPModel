package com.gaolei.mvpmodel.api;


import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @GET("project/list/{page}/json")
    Observable<ProjectListData> getProjectListData(@Path("page") int page, @Query("cid") int cid);
/*
    http://www.wanandroid.com/banner/json
    广告栏数据
*/

    @GET("banner/json")
    Observable<BannerListData> getBannerListData();
}
