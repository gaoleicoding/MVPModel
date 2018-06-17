package com.gaolei.mvpmodel.rxjava;

import android.app.Dialog;
import android.widget.Toast;

import com.gaolei.mvpmodel.application.CustomApplication;
import com.gaolei.mvpmodel.mview.BaseView;
import com.gaolei.mvpmodel.view.CustomProgressDialog;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    protected String errMsg = "";
    private BaseView mView;
    private boolean isShowError = true;

    protected BaseObserver(BaseView view){
        this.mView = view;
//        Dialog mDialog = CustomProgressDialog.createLoadingDialog(this, "正在加载中...");
//        mDialog.setCancelable(true);//允许返回
//        mDialog.show();//显示
    }
    protected BaseObserver(BaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }



    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        if (!CustomApplication.isNetworkAvalible()) {
            errMsg = "网络连接出错,请检查网络";
        } else if (e instanceof HttpException) {
            errMsg = "请求服务器出错,";
        } else if (e instanceof IOException) {
            errMsg = "网络出错,";
        }
        if (isShowError) {
            mView.showErrorMsg(errMsg);
        }

    }

    @Override
    public void onComplete() {

    }

}
