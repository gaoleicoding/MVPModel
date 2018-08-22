package com.gaolei.mvpmodel.base.mview;

public interface BaseView {

    void showLoading();
    void hideLoading();
    void showErrorMsg(String errorMsg);
}