package com.gaolei.mvpmodel.base.thirdframe.rxjava;

import android.content.Context;

import com.gaolei.mvpmodel.base.utils.NetUtils;
import com.gaolei.mvpmodel.base.utils.Utils;
import com.gaolei.mvpmodel.base.view.CustomProgressDialog;

import java.io.IOException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    protected String errMsg = "";
    private boolean isShowError = true;
    private Context context;

    protected BaseObserver(boolean isShowDialog){

    }



    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        CustomProgressDialog.cancel();
        if (!NetUtils.isConnected()) {
            errMsg = "网络连接出错,请检查网络";

        } else if (e instanceof HttpException) {
            errMsg = "服务器访问异常(HttpException)";
        } else if (e instanceof IOException) {
            errMsg = "服务器访问异常(IOException)";
        }

            Utils.showToast(errMsg);

    }

    @Override
    public void onComplete() {
        CustomProgressDialog.cancel();
    }

}
