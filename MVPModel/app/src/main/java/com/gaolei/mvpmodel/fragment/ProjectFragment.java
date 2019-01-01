package com.gaolei.mvpmodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.activity.ArticleDetailActivity;
import com.gaolei.mvpmodel.adapter.DividerItemDecoration;
import com.gaolei.mvpmodel.adapter.ProjectListAdapter;
import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.base.mmodel.ProjectListData;
import com.gaolei.mvpmodel.base.view.CustomProgressDialog;
import com.gaolei.mvpmodel.mcontract.ProjectContract;
import com.gaolei.mvpmodel.mpresenter.ProjectPresenter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class ProjectFragment extends BaseMvpFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.project_recyclerview)
    RecyclerView project_recyclerview;
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;
    ProjectListAdapter projectAdapter;
    private List<ProjectListData.ProjectData> projectDataList;

    @Override
    public void initData(Bundle bundle) {


        mPresenter.getProjectInfo(1, 294);

    }

    @Override
    public void initView() {
        initSmartRefreshLayout();
        initRecyclerView();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void reload() {
        if(mPresenter==null)return;
        mPresenter.getProjectInfo(1, 294);
    }

    @Override
    public ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void loadData() {
        CustomProgressDialog.show(getActivity());
        mPresenter.getProjectInfo(1, 294);
    }


    @Override
    public void showProjectList(ProjectListData listData, boolean isRefresh) {
        final List<ProjectListData.ProjectData> newDataList = listData.data.getDatas();

        if (isRefresh) {
//            mAdapter.replaceData(feedArticleListData.getDatas());
            smartRefreshLayout.finishRefresh(true);
        } else {
            projectDataList.addAll(newDataList);
            projectAdapter.notifyItemRangeInserted(projectDataList.size() - newDataList.size(), newDataList.size());
            projectAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        }

        projectAdapter.setOnItemClickListener(new ProjectListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", projectDataList.get(position).getLink());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        projectDataList = new ArrayList<>();
        projectAdapter = new ProjectListAdapter(getActivity(), projectDataList);
        project_recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        project_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        project_recyclerview.setAdapter(projectAdapter);
    }

    //初始化下拉刷新控件
    private void initSmartRefreshLayout() {
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        smartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresenter.onLoadMore(294);
            }


        });
    }
    public void scrollToTop(){
        project_recyclerview.scrollToPosition(0);
    }
}
