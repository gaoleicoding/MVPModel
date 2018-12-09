package com.gaolei.mvpmodel.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.activity.ArticleDetailActivity;
import com.gaolei.mvpmodel.adapter.ArticleListAdapter;
import com.gaolei.mvpmodel.adapter.DividerItemDecoration;
import com.gaolei.mvpmodel.base.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.base.mmodel.ArticleListData;
import com.gaolei.mvpmodel.base.mmodel.ArticleListData.FeedArticleData;
import com.gaolei.mvpmodel.base.mmodel.BannerListData;
import com.gaolei.mvpmodel.mcontract.HomeContract;
import com.gaolei.mvpmodel.mpresenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author quchao
 * @date 2018/2/11
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.article_recyclerview)
    RecyclerView article_recyclerview;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;
    private List<FeedArticleData> articleDataList;
    private ArticleListAdapter feedArticleAdapter;
//    @Inject
//    HomePresenter homePresenter;

    @Override
    public void initData(Bundle bundle) {

        Debug.startMethodTracing("traceview");

        Debug.stopMethodTracing();

    }

    @Override
    public void initView() {
        initSmartRefreshLayout();
        initRecyclerView();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void reload() {
        mPresenter.getFeedArticleList(0);
        mPresenter.getBannerInfo();
    }

//    @Override
//    public BasePresenter initPresenter() {
//        return new HomePresenter();
//    }

    @Override
    protected void loadData() {
        mPresenter.getFeedArticleList(0);
        mPresenter.getBannerInfo();

    }


    @Override
    public void showArticleList(ArticleListData listData, boolean isRefresh) {
        final List<FeedArticleData> newDataList = listData.data.getDatas();
        if (isRefresh) {
            smartRefreshLayout.finishRefresh(true);
        } else {
            articleDataList.addAll(newDataList);
            feedArticleAdapter.notifyItemRangeInserted(articleDataList.size() - newDataList.size(), newDataList.size());
            feedArticleAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        }

        feedArticleAdapter.setOnItemClickListener(new ArticleListAdapter.OnItemClickListener() {
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

    @Override
    public void showBannerList(BannerListData itemBeans) {

        final List<String> linkList = new ArrayList<String>();
        List imageList = new ArrayList();
        List titleList = new ArrayList();
        int size = itemBeans.data.size();

        for (int i = 0; i < size; i++) {
            imageList.add(itemBeans.data.get(i).getImagePath());
            titleList.add(itemBeans.data.get(i).getTitle());
            linkList.add(itemBeans.data.get(i).getUrl());
        }
        banner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }
        });

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器与标题
        //设置banner动画效果
//        Tansformer.CubeIn
//        Transformer.CubeOut
//        Transformer.DepthPage
//        Transformer.FlipHorizontal
//        Transformer.FlipVertical
        banner.setBannerAnimation(Transformer.FlipHorizontal);
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        banner.setDelayTime(3000);//设置轮播时间
        banner.setImages(imageList);//设置图片源
        banner.setBannerTitles(titleList);//设置标题源

        banner.start();


        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", linkList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        articleDataList = new ArrayList<>();
        feedArticleAdapter = new ArticleListAdapter(getActivity(), articleDataList);
        article_recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        article_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        article_recyclerview.setAdapter(feedArticleAdapter);
        article_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()) {
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });
    }

    //初始化下拉刷新控件
    private void initSmartRefreshLayout() {
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        smartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresenter.onLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.onRefreshMore();
            }
        });
    }

    public void scrollToTop() {
        article_recyclerview.scrollToPosition(0);
    }

    public void onResume() {
        super.onResume();

    }

    public void onDestroy() {
        super.onDestroy();
    }

}
