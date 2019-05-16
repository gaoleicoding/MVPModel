package com.gaolei.mvpmodel.fragment;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.activity.ArticleDetailActivity;
import com.gaolei.mvpmodel.adapter.DividerItemDecoration;
import com.gaolei.mvpmodel.adapter.ProjectAdapter;
import com.gaolei.mvpmodel.databinding.FragmentHomeBinding;
import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.mpresenter.HomePresenter;
import com.gaolei.mvpmodel.mview.ProjectListView;
import com.gaolei.mvpmodel.viewmodel.BannerViewModel;
import com.gaolei.mvpmodel.viewmodel.ProjectViewModel;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {

    private static final String KEY_PROJECT_ID = "project_id";
    ProjectAdapter projectAdapter;
    FragmentHomeBinding binding;

    @Override
    public View getContentLayout(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void reload() {

    }

    @Override
    public void initData(Bundle bundle) {
//        mPresenter.getProjectInfo(1, 294);
//        mPresenter.getBannerInfo();
        final ProjectViewModel viewModel = ViewModelProviders.of(this)
                .get(ProjectViewModel.class);

        observeViewModel(viewModel);
        viewModel.setProjectParams(new ProjectViewModel.ProjectParams(1, 294));


        final BannerViewModel bannerViewModel = ViewModelProviders.of(this)
                .get(BannerViewModel.class);
        observeBannerViewModel(bannerViewModel);
    }

    private void observeViewModel(final ProjectViewModel viewModel) {
        // Observe project data
        viewModel.getObservableProject().observe(this, new Observer<ProjectListData>() {
            @Override
            public void onChanged(@Nullable ProjectListData listData) {
                if (listData != null) {
                    final List<ProjectListData.FeedArticleData> articleDataList = listData.data.getDatas();
                    Log.d("gaolei", "articleDataList.size():" + articleDataList.size());
                    projectAdapter = new ProjectAdapter(getActivity(), articleDataList);

                    binding.projectRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                            DividerItemDecoration.VERTICAL_LIST));
                    binding.projectRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.projectRecyclerview.setAdapter(projectAdapter);
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
        });
    }

    private void observeBannerViewModel(final BannerViewModel viewModel) {
        // Observe project data
        viewModel.getObservableProject().observe(this, new Observer<BannerListData>() {
            @Override
            public void onChanged(@Nullable BannerListData listData) {
                if (listData != null) {
                    requstBannerList(listData);
                }
            }
        });
    }

    public void requstBannerList(BannerListData itemBeans) {

        final List<String> linkList = new ArrayList<String>();
        List imageList = new ArrayList();
        List titleList = new ArrayList();
        int size = itemBeans.data.size();
        Log.d("gaolei", "url--------------" + itemBeans.data.get(0).getUrl());
        Log.d("gaolei", "title--------------" + itemBeans.data.get(0).getTitle());
        for (int i = 0; i < size; i++) {
            imageList.add(itemBeans.data.get(i).getImagePath());
            titleList.add(itemBeans.data.get(i).getTitle());
            linkList.add(itemBeans.data.get(i).getUrl());
        }
        binding.banner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
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
        binding.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器与标题
        //设置banner动画效果
//        Tansformer.CubeIn
//        Transformer.CubeOut
//        Transformer.DepthPage
//        Transformer.FlipHorizontal
//        Transformer.FlipVertical
        binding.banner.setBannerAnimation(Transformer.FlipHorizontal);
        binding.banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        binding.banner.setDelayTime(3000);//设置轮播时间
        binding.banner.setImages(imageList);//设置图片源
        binding.banner.setBannerTitles(titleList);//设置标题源

        binding.banner.start();


        binding.banner.setOnBannerListener(new OnBannerListener() {
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

}
