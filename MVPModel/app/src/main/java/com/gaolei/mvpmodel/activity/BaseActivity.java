package com.gaolei.mvpmodel.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by gaolei on 2018/4/26.
 */

public abstract class BaseActivity extends FragmentActivity {
    private Unbinder mBinder;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        setStatusBarColor(R.color.yellow);
        mBinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);
        View view =LayoutInflater.from(this).inflate(setContentLayout(),null);

    }

    protected abstract int setContentLayout();

    protected abstract void initData(Bundle bundle);

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor(int resColor) {
        StatusBarUtil.setWindowStatusBarColor(this, resColor, true);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }
}
