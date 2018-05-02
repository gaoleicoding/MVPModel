package com.gaolei.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getProjectInfo(1, 294);

    }

    public void getProjectInfo(int page, int cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestService userBiz = retrofit.create(RestService.class);
        Call<ProjectListData> call = userBiz.getProjectListData(page, cid);
        call.enqueue(new Callback<ProjectListData>() {
            @Override
            public void onResponse(Call<ProjectListData> call, Response<ProjectListData> response) {
//                Log.d("gaolei", "onResponse:" + response.body().toString());
//                Log.d("gaolei","getSize-----------"+response.body().getSize());
//                Log.d("gaolei","getPageCount---------"+response.body().getPageCount());
//                Log.d("gaolei","getCurPage-----------"+response.body().getCurPage());
                ProjectListData projectListData=response.body();
                Log.d("gaolei","projectListData.getSize()------------"+projectListData.data.getSize());

                List<FeedArticleData> articleDataList=response.body().data.getDatas();
                Log.d("gaolei","articleDataList.size()------------"+articleDataList.size());
            }

            @Override
            public void onFailure(Call<ProjectListData> call, Throwable t) {
                Log.d("gaolei", "onFailure:" + t.toString());

            }
        });

    }
}
