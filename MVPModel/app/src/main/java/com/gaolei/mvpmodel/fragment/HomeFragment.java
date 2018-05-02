package com.gaolei.mvpmodel.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.adapter.MyRouteAdapter;
import com.gaolei.mvpmodel.adapter.MyRouteDividerDecoration;
import com.gaolei.mvpmodel.mmodel.FeedArticleData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mpresenter.HomePresenter;
import com.gaolei.mvpmodel.mview.BankListView;

import java.util.List;


/**
 * @author quchao
 * @date 2018/2/11
 */

public class HomeFragment extends BaseMvpFragment<BankListView, HomePresenter> implements BankListView {


    RecyclerView project_recyclerview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);

        project_recyclerview=view.findViewById(R.id.project_recyclerview);
        mPresenter.getProjectInfo(1,294);

        return view;
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void requstBankList(ProjectListData listData) {
        MyRouteAdapter routeAdapter = new MyRouteAdapter(getActivity(), listData.data.getDatas());
        Log.d("gaolei","getSize-----------"+listData.data.getSize());
        Log.d("gaolei","getPageCount---------"+listData.data.getPageCount());
        Log.d("gaolei","getCurPage-----------"+listData.data.getCurPage());
        Log.d("gaolei",""+listData.data.getDatas().size());
        project_recyclerview.setAdapter(routeAdapter);
        project_recyclerview.addItemDecoration(new MyRouteDividerDecoration(10));
        project_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        routeAdapter.setOnItemClickListener(new MyRouteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
    }
}
