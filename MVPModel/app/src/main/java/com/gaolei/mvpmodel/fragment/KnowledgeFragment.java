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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment1 = inflater.inflate(R.layout.fragment_knowledge, null);
        return fragment1;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
