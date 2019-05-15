package com.gaolei.mvpmodel.repository;


import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.net.RestApiProvider;
import com.gaolei.mvpmodel.net.RestService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerRepository {
    private RestService gitHubService = RestApiProvider.getInstance().builder().getApiService();


    public LiveData<BannerListData> getBanner() {
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
