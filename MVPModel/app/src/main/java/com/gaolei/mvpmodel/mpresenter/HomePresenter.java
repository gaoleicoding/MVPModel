package com.gaolei.mvpmodel.mpresenter;


import android.util.Log;

import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mview.BankListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caiwancheng on 2017/8/30.
 */

public class HomePresenter extends BasePresenter<BankListView> {
    /**
     * 加载银行列表
     */
//    public void loadBankList(final BaseMVPFragment fragment) {
//        mView.showLoading();
//        RestService restService = RestApiProvider.getInstance().getApiService(RestService.class);
//
//        mCall = restService.postData(UrlConfig.BANK_LIST, new BaseRequest());
//        mCall.enqueue(new RestBaseCallBack<List<BankInfoResponse>>() {
//            @Override
//            public void onResponse(List<BankInfoResponse> itemBeans) {
//                mView.hideLoading();
//                if (!ExtendUtil.listIsNullOrEmpty(itemBeans)) {
//                    mView.requstBankList(itemBeans);
//                }
//            }
//
//
//            @Override
//            public void onFailure(Throwable error, int code, String msg) {
//                if (mView != null) {
//                    if (!ErrorMsgUtils.showNetErrorPageOrLogin(fragment, code, msg)) {
//                        ToastUtil.showShortToast(FJDApplication.getZMApplication(), msg);
//                    }
//                    mView.hideLoading();
//                }
//            }
//        });


//    }
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://192.168.31.242:8080/springmvc_users/user/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//    IUserBiz userBiz = retrofit.create(IUserBiz.class);
    public void getProjectInfo(int page, int cid) {

        mCall = mRestService.getProjectListData(page, cid);
        mView.showLoading();
        mCall.enqueue(new Callback<ProjectListData>() {
            public void onResponse(Call<ProjectListData> call, Response<ProjectListData> response) {
                mView.hideLoading();
//                try {
//                    Log.d("gaolei", "response-------------:" + response.toString());
//
//                    JSONObject object = new JSONObject(response.toString());
//                    JSONObject object2 = object.getJSONObject("data");
//                    Log.d("gaolei", "object2-------------:" + object2.toString());
//                    JSONObject object3 = object2.getJSONObject("datas");
//                    Log.d("gaolei", "object3-------------:" + object3.toString());
//
//                    List<ProjectInfo> projectInfoList = new Gson().fromJson(object3.toString(), new TypeToken<List<ProjectInfo>>() {
//                    }.getType());
//
                    mView.requstBankList(response.body());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.d("gaolei", "JSONException.toString-------------:" +e.toString());
//
//                }

            }

            public void onFailure(Call<ProjectListData> call, Throwable t) {
                mView.hideLoading();

                Log.d("gaolei", "mCall-error.toString-------------:" + t.toString());

            }
        });
    }
}
