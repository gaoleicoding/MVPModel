package com.gaolei.mvpmodel.rxjava;

import android.app.Dialog;
import android.content.Context;

import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.mview.BaseView;
import com.gaolei.mvpmodel.utils.Utils;
import com.gaolei.mvpmodel.view.CustomProgressDialog;

import java.io.IOException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    protected String errMsg = "";
    private boolean isShowError = true;
    private Context context;
    Dialog prgressDialog;

    protected BaseObserver(Context context){
        this.context=context;
        prgressDialog= CustomProgressDialog.createLoadingDialog(context);
        prgressDialog.setCancelable(true);//允许返回
        prgressDialog.show();//显示
    }
    protected BaseObserver(BaseView view, boolean isShowError){
        this.isShowError = isShowError;
    }



    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        prgressDialog.cancel();
        if (!CustomApplication.isNetworkAvalible()) {
            errMsg = "网络连接出错,请检查网络";

        } else if (e instanceof HttpException) {
            errMsg = "请求服务器出错,";
        } else if (e instanceof IOException) {
            errMsg = "网络出错,";
        }
        if (isShowError) {
//            mView.showErrorMsg(errMsg);
            Utils.showToast(context,errMsg);
        }

    }

    @Override
    public void onComplete() {
        prgressDialog.cancel();
    }

}
