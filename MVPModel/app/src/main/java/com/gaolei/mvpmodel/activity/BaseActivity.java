package com.gaolei.mvpmodel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.MemoryFile;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.utils.NetworkUtil;
import com.gaolei.mvpmodel.utils.PermissionUtil;
import com.gaolei.mvpmodel.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.gaolei.mvpmodel.utils.PermissionUtil.PERMISSION_CODE;


/**
 * Created by gaolei on 2018/4/26.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private PermissionUtil.RequestPermissionCallBack mRequestPermissionCallBack;

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

    /**
     * 发起权限请求
     *
     * @param context
     * @param permissions
     * @param callback
     */

    public void requestPermission(final Context context, final String[] permissions,
                                  PermissionUtil.RequestPermissionCallBack callback) {
        this.mRequestPermissionCallBack = callback;

        //如果所有权限都已授权，则直接返回授权成功,只要有一项未授权，则发起权限请求
        boolean isAllGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                isAllGranted = false;
                ActivityCompat.requestPermissions(((Activity) context), permissions, PERMISSION_CODE);
            }
        }
        if (isAllGranted) {
            mRequestPermissionCallBack.granted();
        }
    }

    /**
     * 权限请求结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;

        switch (requestCode) {
            case PERMISSION_CODE: {
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        hasAllGranted = false;
                        //在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false则
                        // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            PermissionUtil.requestForeverDenyDialog(BaseActivity.this, permissions);

                        } else {
                            PermissionUtil.requestDenyDialog(BaseActivity.this, permissions);

                            //用户拒绝权限请求，但未选中“不再提示”选项
                        }
                        mRequestPermissionCallBack.denied();
                        return;
                    }
                }
                if (hasAllGranted) {
                    mRequestPermissionCallBack.granted();
                }
            }
        }
    }

}
