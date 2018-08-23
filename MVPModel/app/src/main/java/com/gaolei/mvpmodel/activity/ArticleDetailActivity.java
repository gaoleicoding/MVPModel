package com.gaolei.mvpmodel.activity;

import android.os.Bundle;
import android.view.View;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.base.activity.BaseActivity;
import com.gaolei.mvpmodel.view.Html5Webview;

import butterknife.BindView;

public class ArticleDetailActivity extends BaseActivity {

    @BindView(R.id.webview_article)
    Html5Webview webview_article;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initData(Bundle bundle) {
        title.setText(getString(R.string.article_detail));
        iv_back.setVisibility(View.VISIBLE);
        String url = bundle.getString("url");
        webview_article.loadUrl(url);
    }

}
