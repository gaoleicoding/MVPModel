package com.gaolei.mvpmodel.mpresenter;

import com.gaolei.mvpmodel.net.RetrofitProvider;
import com.gaolei.mvpmodel.net.ApiService;
import com.gaolei.mvpmodel.rxjava.BaseObserver;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V> {

    public V mView;
//    public Observable observable;
    public ApiService mRestService = RetrofitProvider.getInstance().builder().getApiService();
    CompositeDisposable mCompositeDisposable ;

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
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void doSubscribe( Observable observable,BaseObserver observer){
                mCompositeDisposable.add(observer);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}