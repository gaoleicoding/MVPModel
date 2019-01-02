package com.gaolei.mvpmodel.base.api;


import com.gaolei.mvpmodel.base.mmodel.FilterTxtEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

//@Api("https://as.dun.163yun.com/")
public interface ApiYidunServie {
    public static String baseUrl="https://as.dun.163yun.com/";
    //易盾敏感词过滤
    @GET("v3/text/check")
    Observable<FilterTxtEntity> filterTxt(@QueryMap Map<String, String> map);
}
