package com.gaolei.mvpmodel.mpresenter;

import com.gaolei.mvpmodel.net.RestApiProvider;
import com.gaolei.mvpmodel.net.RestService;

import retrofit2.Call;

public abstract class BasePresenter<V> {

    public V mView;
    public Call mCall;
    public RestService mRestService = RestApiProvider.getInstance().withNoInterceptor().builder().getApiService();


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
        if (mCall != null) {
            mCall.cancel();
        }
    }
}