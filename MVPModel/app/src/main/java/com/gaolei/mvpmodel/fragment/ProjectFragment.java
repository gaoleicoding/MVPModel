package com.gaolei.mvpmodel.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.databinding.FragmentProjectBinding;
import com.gaolei.mvpmodel.mpresenter.BasePresenter;


public class ProjectFragment extends BaseMvpFragment {

    FragmentProjectBinding binding;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public View getContentLayout(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false);
        return binding.getRoot();
    }


    @Override
    public void reload() {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
