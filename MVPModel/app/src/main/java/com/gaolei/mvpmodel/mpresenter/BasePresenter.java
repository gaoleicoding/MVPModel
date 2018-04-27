package com.gaolei.mvpmodel.mpresenter;

import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.net.RestApiProvider;
import com.gaolei.mvpmodel.net.RestService;
import com.gaolei.mvpmodel.net.SignInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BasePresenter<V> {

    public V mView;
//    public Call<BaseServerResponse> mCall;
    protected RestService mRestService = RestApiProvider.getInstance().withNoInterceptor().builder().getApiService();


    /**
     * 绑定View
     *
     * @param view
     */
    public void attach(V view) {
        this.mView = view;
    }

    /**
     * 释放View
     */
    public void dettach() {
        this.mView = null;

    }
}