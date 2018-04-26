//package com.gaolei.mvpmodel;
//
//import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//
//public class SampleActivity extends BaseActivity {
//
//    public static final String FRAGMENT_TAG = "fragment_tag";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_layout_detail);
//        init();
//    }
//
//    private void init() {
//        //初始化view
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        SampleFragment fragment = (SampleFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//        if (fragment == null) {
//            fragment = SampleFragment.newInstance(param1, param2);
//            fragmentManager.beginTransaction().add(R.id.fl_container, fragment, FRAGMENT_TAG).commit();
//        }
//
//        //初始化presenter
//        new SamplePresenterImpl(fragment);
//    }
//}
//
