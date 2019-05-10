package com.gaolei.mvpmodel.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.databinding.ActivityArticleDetailBinding;

public class ArticleDetailActivity extends BaseActivity {

    ActivityArticleDetailBinding binding;
    @Override
    protected void initData(Bundle bundle) {
        binding=DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        binding.title.setText(getString(R.string.article_detail));
        binding.ivBack.setVisibility(View.VISIBLE);
        String url = bundle.getString("url");
        binding.webviewArticle.loadUrl(url);
    }

}
