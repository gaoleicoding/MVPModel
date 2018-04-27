package com.gaolei.mvpmodel.net;


import com.gaolei.mvpmodel.mmodel.ProjectInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

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

        @GET("users")
        Call<List<ProjectInfo>> getUsers();
}
