package com.gaolei.mvpmodel.base.mpresenter;

import com.gaolei.mvpmodel.base.api.ApiService;
import com.gaolei.mvpmodel.base.thirdframe.retrofit.RetrofitProvider;
import com.gaolei.mvpmodel.base.thirdframe.rxjava.BaseObserver;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V> {

    public V mView;
    public ApiService mRestService = RetrofitProvider.getInstance().builder().getApiService();
    CompositeDisposable mCompositeDisposable;

    /**
     * 绑定View
     *
     * @param view
     */
    public void attach(V view) {
        this.mView = view;
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    /**
     * 释放View
     */
    public void dettach() {
        this.mView = null;
        //避免内存泄漏，view销毁的时候解除，取消所有observer
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void addSubscribe(Observable observable, BaseObserver observer) {
        //把所有observer都放到一个集合中
        mCompositeDisposable.add(observer);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}