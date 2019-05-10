package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.mpresenter.BasePresenter;




public class KnowledgeFragment extends BaseMvpFragment {

    @Override
    public void initData( Bundle bundle) {

    }

    @Override
    public View getContentLayout(LayoutInflater inflater, ViewGroup container) {
        return new View(getContext());
    }


    @Override
    public void reload() {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
