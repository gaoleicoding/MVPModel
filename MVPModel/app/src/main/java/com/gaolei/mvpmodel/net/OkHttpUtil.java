package com.gaolei.mvpmodel.net;


import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gaolei on 2018/4/27.
 */

public class OkHttpUtil {
    //静态本类对象
    private static OkHttpUtil okHttpUtil;
    private OkHttpClient okHttpClient;

    //私有化构造方法
    private OkHttpUtil() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    //公共的供外部访问的方法
    public static OkHttpUtil getInstance() {
        if (okHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    public void get(String url, Map<String, String> map, final NetworkCallback networkCallback) {

        if (map != null && map.size() > 0) {

            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> set = map.keySet();
            for (String str : set) {

                sb.append(str).append("=").append(map.get(str)).append("&");
            }
            url = sb.substring(0, sb.length() - 1);
        }

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                networkCallback.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                networkCallback.successful(string);
            }
        });
    }

    public void post(String url, Map<String, String> map, final NetworkCallback networkCallback) {
        if (map != null && map.size() > 0) {

            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> set = map.keySet();
            for (String str : set) {

                sb.append(str).append("=").append(map.get(str)).append("&");
            }
            url = sb.substring(0, sb.length() - 1);
        }

        RequestBody body = new FormBody.Builder().build();
        Request request = new Request.Builder().url(url).method("POST", body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                networkCallback.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                networkCallback.successful(string);
            }
        });
    }

    public interface NetworkCallback{
         void error(String error);
         void successful(String response);
    }
}