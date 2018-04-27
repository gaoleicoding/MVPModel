package com.gaolei.mvpmodel.mpresenter;



import android.util.Log;

import com.gaolei.mvpmodel.fragment.BaseMVPFragment;
import com.gaolei.mvpmodel.mview.BankListView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.31.242:8080/springmvc_users/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    IUserBiz userBiz = retrofit.create(IUserBiz.class);
    Call<List<User>> call = userBiz.getUsers();
        call.enqueue(new Callback<List<User>>()
    {
        @Override
        public void onResponse(Call<List<User>> call, Response<List<User>> response)
        {
            Log.e(TAG, "normalGet:" + response.body() + "");
        }

        @Override
        public void onFailure(Call<List<User>> call, Throwable t)
        {

        }
    });

}
