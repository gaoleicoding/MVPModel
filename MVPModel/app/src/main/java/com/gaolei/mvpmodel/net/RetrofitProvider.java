package com.gaolei.mvpmodel.net;


import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.net.interceptor.GzipRequestInterceptor;
import com.gaolei.mvpmodel.net.interceptor.HttpLoggingInterceptor;
import com.gaolei.mvpmodel.net.interceptor.OfflineCacheInterceptor;
import com.gaolei.mvpmodel.net.interceptor.OnlineCacheInterceptor;
import com.gaolei.mvpmodel.net.interceptor.RetryIntercepter;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RetrofitProvider {

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private static volatile RetrofitProvider sInstance;
    private ApiService restService;

    private RetrofitProvider() {
    }


    public RetrofitProvider builder() {

        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor())
                    .addNetworkInterceptor(new OnlineCacheInterceptor())//有网缓存拦截器
                    .addInterceptor(new OfflineCacheInterceptor())//无网缓存拦截器
                    .cache(new Cache(new File(CustomApplication.cacheDir), 50 * 10240 * 1024))//缓存路径和空间设置
                    .addInterceptor(new RetryIntercepter(4))//重试
                    .addInterceptor(new GzipRequestInterceptor())//开启Gzip压缩

//                    .addInterceptor(new DefaultHeaderInterceptor())//请求连接中添加头信息
//                    .addInterceptor(new ProgressInterceptor())//请求url的进度
//                    .addInterceptor(new TokenInterceptor())//token过期，自动刷新Token
//                    .addInterceptor(new SignInterceptor())//所有的接口，默认需要带上sign,timestamp2个参数

                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build();

        }
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(UrlConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sInstance;
    }

    public static RetrofitProvider getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitProvider.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitProvider();
                }
            }
        }
        return sInstance;
    }

    public ApiService getApiService() {
        if (restService == null)
            restService = mRetrofit.create(ApiService.class);
        return restService;
    }
}