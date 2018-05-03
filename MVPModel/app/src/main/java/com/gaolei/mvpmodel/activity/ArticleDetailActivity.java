package com.gaolei.mvpmodel.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gaolei.mvpmodel.BottomNavigationViewHelper;
import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.fragment.BaseMvpFragment;
import com.gaolei.mvpmodel.fragment.HomeFragment;
import com.gaolei.mvpmodel.fragment.KnowledgeFragment;
import com.gaolei.mvpmodel.fragment.NavigationFragment;
import com.gaolei.mvpmodel.fragment.ProjectFragment;
import com.gaolei.mvpmodel.view.Html5Webview;

import java.util.ArrayList;

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
