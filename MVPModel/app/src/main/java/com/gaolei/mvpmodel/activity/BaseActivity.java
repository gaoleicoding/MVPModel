package com.gaolei.mvpmodel.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.utils.NetworkUtil;
import com.gaolei.mvpmodel.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by gaolei on 2018/4/26.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private Unbinder mBinder;
    ImageView iv_back;
    TextView title;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        setContentView(addContentView(inflater));
        setStatusBarColor(R.color.yellow);
        mBinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);


    }

    protected abstract int setContentLayout();

    protected abstract void initData(Bundle bundle);

    private void initBaseView(View view) {
        iv_back = view.findViewById(R.id.iv_back);
        title = view.findViewById(R.id.title);
        iv_back.setOnClickListener(this);
    }


    public View addContentView(LayoutInflater inflater) {
        LinearLayout mParentView = (LinearLayout) inflater.inflate(R.layout.activity_base, null);
        View subActivityView = inflater.inflate(setContentLayout(), null);
        mParentView.addView(subActivityView);
        initBaseView(mParentView);
        return mParentView;
    }

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
