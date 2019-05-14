package com.gaolei.mvpmodel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by gaolei on 2018/4/26.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    Unbinder mBinder;
    ImageView ivBack;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(R.color.yellow);
        mBinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
