package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.utils.NetworkUtil;
import com.gaolei.mvpmodel.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by liuhaiyang on 2017/1/2.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private FrameLayout mLlContent;
    View subFragmentView;
    private RelativeLayout mLlLoading;
    private Button bt_error_refresh;
    public LinearLayout mErrorPageView;
    private Unbinder mBinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mParentView = inflater.inflate(R.layout.fragment_base, container, false);
        initBaseView(mParentView);
        addContentView(inflater);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);
        return mParentView;
    }

    /**
     * 用于布局加载完毕，子Fragment可以开始初始化数据
     *
     * @param bundle
     */
    public abstract void initData(Bundle bundle);

    private void initBaseView(View view) {
        mLlContent = view.findViewById(R.id.base_fragment_content);
        mErrorPageView = view.findViewById(R.id.ll_base_error_content);
        bt_error_refresh = view.findViewById(R.id.bt_error_refresh);
        mLlLoading = view.findViewById(R.id.ll_loading);
        if (!NetworkUtil.isNetworkAvailable(getActivity()))
            mErrorPageView.setVisibility(View.VISIBLE);
        bt_error_refresh.setOnClickListener(this);
    }

    /**
     * 设置子布局layout
     */
    public abstract int setContentLayout();

    public abstract void reload();

    /**
     * 设置内容
     */
    public void addContentView(LayoutInflater inflater) {
        subFragmentView = inflater.inflate(setContentLayout(), null);
        mLlContent.addView(subFragmentView);
        mBinder = ButterKnife.bind(this, subFragmentView);

    }

    /**
     * 显示加载进度条
     *
     * @param isShow
     */
    public void setLoading(boolean isShow) {
        mLlLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    /**
     * 显示/隐藏 错误页面
     *
     * @param isShow
     */
    public void showErrorPage(boolean isShow) {
//        mErrorPageView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_error_refresh:
                if (NetworkUtil.isNetworkAvailable(getActivity()))
                    mErrorPageView.setVisibility(View.GONE);
                reload();
                break;
        }
    }

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor(int resColor) {
        StatusBarUtil.setWindowStatusBarColor(getActivity(), resColor, true);
    }
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }
}
