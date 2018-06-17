package com.gaolei.mvpmodel.utils;

import android.content.Context;
import android.widget.Toast;

import java.security.MessageDigest;

/**
 * Created by gaolei on 2018/6/15.
 */

public class Utils {

    public static void showToast(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
    /**
     * md5 加密
     *
     * @param str 要加密的字符串
     * @return
     */
    public static String md5Encode(String str) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] bytes = md5.digest();
            for (int i = 0; i < bytes.length; i++) {
                String s = Integer.toHexString(bytes[i] & 0xff);
                if (s.length() == 1) {
                    buf.append("0");
                }
                buf.append(s);
            }

        } catch (Exception ex) {
        }
        return buf.toString();
    }

}
