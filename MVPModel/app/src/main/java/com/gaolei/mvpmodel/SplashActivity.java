package com.gaolei.mvpmodel;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gaolei.mvpmodel.base.activity.BaseActivity;
import com.gaolei.mvpmodel.base.utils.PermissionUtil;

/**
 * description: test
 * author: zlm
 * date: 2017/3/17 16:01
 */
public class SplashActivity extends BaseActivity {


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };


    @Override
    protected int setContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(Bundle bundle) {
        requestPermission();
    }

    public void requestPermission() {
        requestPermission(this, new PermissionUtil.RequestPermissionCallBack() {

            @Override
            public void granted() {
                handler.sendEmptyMessageDelayed(0, 3000);
            }

            @Override
            public void denied() {
            }
        }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE});
    }

    public void onRestart() {
        super.onRestart();
        //跳转到设置界面后返回，重新检查权限
        requestPermission();
    }

}
