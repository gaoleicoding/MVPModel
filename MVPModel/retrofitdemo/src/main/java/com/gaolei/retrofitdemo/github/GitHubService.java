package com.gaolei.retrofitdemo.github;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gaolei on 2018/5/2.
 */

public interface GitHubService {
    @GET("orgs/{orgName}/repos")
    Call<List<RepositoryBean>> queryOrgRepos(
            @Path("orgName") String orgName);




}
