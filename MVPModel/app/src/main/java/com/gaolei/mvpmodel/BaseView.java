package com.gaolei.mvpmodel;

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
}