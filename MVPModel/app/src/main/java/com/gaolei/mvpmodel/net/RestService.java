package com.gaolei.mvpmodel.net;


import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liuhaiyang on 2016/9/6.
 */
public interface RestService {
//    @GET
//    Call<BaseServerResponse> loadHomeList(@Url String url, @Query("strRecord") int strRecord, @Query("pageSize") int pageSize);
//
//    @GET
//    Call<BaseServerResponse> loadData(@Url String url, @QueryMap Map<String, Object> map);
//
//    @GET
//    Call<BaseServerResponse> loadData(@Url String url);
//
//
//    @POST
//    Call<BaseServerResponse> postData(@Url String url, @Body Object body);
//
//
//    @FormUrlEncoded
//    @POST
//    Call<BaseServerResponse> login(@Url String url, @Field("username") String username,
//                                   @Field("password") String pwd
//    );
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
