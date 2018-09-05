package com.gaolei.mvpmodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.activity.ArticleDetailActivity;
import com.gaolei.mvpmodel.adapter.DividerItemDecoration;
import com.gaolei.mvpmodel.adapter.ProjectAdapter;
import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.base.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mcontract.ProjectContract;
import com.gaolei.mvpmodel.mpresenter.ProjectPresenter;

import java.util.List;

import butterknife.BindView;


/**
 * @author quchao
 * @date 2018/2/11
 */

public class ProjectFragment extends BaseMvpFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.project_recyclerview)
    RecyclerView project_recyclerview;

    ProjectAdapter projectAdapter;

    @Override
    public void initData(Bundle bundle) {


        mPresenter.getProjectInfo(1, 294, getActivity());

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void reload() {
        mPresenter.getProjectInfo(1, 294, getActivity());
    }

    @Override
    public ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void loadData() {
        mPresenter.getProjectInfo(1, 294, getActivity());
    }


    @Override
    public void showProjectInfo(ProjectListData listData) {
        final List<ProjectListData.ProjectData> articleDataList = listData.data.getDatas();
        projectAdapter = new ProjectAdapter(getActivity(), articleDataList);

        project_recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        project_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        project_recyclerview.setAdapter(projectAdapter);
        projectAdapter.setOnItemClickListener(new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", articleDataList.get(position).getLink());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
