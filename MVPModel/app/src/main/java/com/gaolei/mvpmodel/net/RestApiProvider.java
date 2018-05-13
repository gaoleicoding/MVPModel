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

    private RestApiProvider() {
    }


    public RestApiProvider withInterceptor(Interceptor intertor, CertificatePinner pinner) {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor())
                    .addInterceptor(intertor)
                    .certificatePinner(pinner)
                    .build();
        }
        return sInstance;
    }

    public RestApiProvider withNoInterceptor() {


        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor())
                    .build();
        }
        return sInstance;
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
                    .baseUrl(UrlConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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