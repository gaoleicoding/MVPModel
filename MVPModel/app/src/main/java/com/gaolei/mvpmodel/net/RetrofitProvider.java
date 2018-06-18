package com.gaolei.mvpmodel.net;


import com.gaolei.mvpmodel.net.interceptor.HttpLoggingInterceptor;
import com.gaolei.mvpmodel.net.interceptor.RetryIntercepter;

import java.util.concurrent.TimeUnit;

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
                    .addInterceptor(new RetryIntercepter(4))//重试
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