package com.gaolei.mvpmodel.mpresenter;


import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mview.ProjectListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caiwancheng on 2017/8/30.
 */

public class HomePresenter extends BasePresenter<ProjectListView> {

    public void getProjectInfo(int page, int cid) {

        mCall = mRestService.getProjectListData(page, cid);
        mView.showLoading();
        mCall.enqueue(new Callback<ProjectListData>() {
            public void onResponse(Call<ProjectListData> call, Response<ProjectListData> response) {
                mView.hideLoading();

                mView.requstProjectList(response.body());

            }

            public void onFailure(Call<ProjectListData> call, Throwable t) {
                mView.hideLoading();

            }
        });
    }
}
