package com.gaolei.retrofitdemo.github;

/**
 * Created by gaolei on 2018/5/2.
 */

public class RepositoryBean {

    String full_name;
    String html_url;

    int contributions;

    @Override
    public String toString() {
        return full_name + " (" + contributions + ")";
    }
}
