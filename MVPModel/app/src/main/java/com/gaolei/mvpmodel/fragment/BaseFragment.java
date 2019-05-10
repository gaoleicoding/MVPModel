package com.gaolei.mvpmodel.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.databinding.FragmentBaseBinding;
import com.gaolei.mvpmodel.utils.NetworkUtil;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    FragmentBaseBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        initBaseView(binding);
        addContentView(inflater, container);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);
        return binding.getRoot();
    }

    /**
     * 用于布局加载完毕，子Fragment可以开始初始化数据
     *
     * @param bundle
     */
    public abstract void initData(Bundle bundle);

    private void initBaseView(FragmentBaseBinding binding) {
        if (!NetworkUtil.isNetworkAvailable(getActivity()))
            binding.baseErrorLayout.llBaseErrorContent.setVisibility(View.VISIBLE);
        binding.baseErrorLayout.btErrorRefresh.setOnClickListener(this);
    }

    /**
     * 设置子布局layout
     */
    public abstract View getContentLayout(LayoutInflater inflater, ViewGroup container);

    public abstract void reload();

    /**
     * 设置内容
     */
    public void addContentView(LayoutInflater inflater, ViewGroup container) {
        binding.baseFragmentContent.addView(getContentLayout(inflater, container));
    }

    /**
     * 显示加载进度条
     *
     * @param isShow
     */
    public void setLoading(boolean isShow) {
        binding.llLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_error_refresh:
                if (NetworkUtil.isNetworkAvailable(getActivity()))
                    binding.baseErrorLayout.llBaseErrorContent.setVisibility(View.GONE);
                reload();
                break;
        }
    }

}
