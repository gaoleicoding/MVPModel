package com.gaolei.mvpmodel.repository;


import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.net.RestApiProvider;
import com.gaolei.mvpmodel.net.RestService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
    private RestService gitHubService = RestApiProvider.getInstance().builder().getApiService();
    ;


    public LiveData<ProjectListData> getProjectInfo(int page, int cid) {
        final MutableLiveData<ProjectListData> data = new MutableLiveData<>();

        gitHubService.getProjectListData(page, cid).enqueue(new Callback<ProjectListData>() {
            @Override
            public void onResponse(Call<ProjectListData> call, Response<ProjectListData> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProjectListData> call, Throwable t) {
                data.setValue(null);
            }


        });

        return data;
    }

    public LiveData<BannerListData> getBannerInfo() {
        final MutableLiveData<BannerListData> data = new MutableLiveData<>();

        gitHubService.getBannerListData().enqueue(new Callback<BannerListData>() {

            @Override
            public void onResponse(Call<BannerListData> call, Response<BannerListData> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BannerListData> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
