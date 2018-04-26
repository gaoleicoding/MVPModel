package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaolei.mvpmodel.BaseFragment;
import com.gaolei.mvpmodel.R;


/**
 * @author quchao
 * @date 2018/2/11
 */

public class NavigationFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment1 = inflater.inflate(R.layout.fragment_navigation, null);
        return fragment1;
    }

}
