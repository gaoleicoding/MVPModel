package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.mpresenter.BasePresenter;


/**
 * @author quchao
 * @date 2018/2/11
 */

public class KnowledgeFragment extends BaseMvpFragment {

    @Override
    public void initData( Bundle bundle) {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void reload() {
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
//    public BasePresenter initPresenter() {
//        return null;
//    }
}
