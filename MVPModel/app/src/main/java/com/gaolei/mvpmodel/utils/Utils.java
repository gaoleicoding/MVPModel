package com.gaolei.mvpmodel.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by gaolei on 2018/6/15.
 */

public class Utils {

    public static void showToast(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
