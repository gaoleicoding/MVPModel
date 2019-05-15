package com.gaolei.mvpmodel.net;


import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RestApiProvider {

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private static volatile RestApiProvider sInstance;
    private RestService restService;
    public  final String BASE_URL = "https://www.wanandroid.com/";

    private RestApiProvider() {
    }


    public RestApiProvider builder() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor())
                    .build();
        }
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }

    public static RestApiProvider getInstance() {
        if (sInstance == null) {
            synchronized (RestApiProvider.class) {
                if (sInstance == null) {
                    sInstance = new RestApiProvider();
                }
            }
        }
        return sInstance;
    }

    public RestService getApiService() {
        if (restService == null)
            restService = mRetrofit.create(RestService.class);
        return restService;
    }
}