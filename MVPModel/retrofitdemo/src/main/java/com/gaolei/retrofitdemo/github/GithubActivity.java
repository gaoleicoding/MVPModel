package com.gaolei.retrofitdemo.github;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gaolei.retrofitdemo.ProjectListData;
import com.gaolei.retrofitdemo.R;
import com.gaolei.retrofitdemo.RestService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaolei on 2018/5/2.
 */

public class GithubActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//                    getContributorListA();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        getContributorListA(1,294);
    }

    public void getContributorListA(int page, int cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestService gitHubService = retrofit.create(RestService.class);

        Call<ProjectListData> call = gitHubService.getProjectListData(page, cid);
        call.enqueue(new Callback<ProjectListData>() {
            @Override
            public void onResponse(Call<ProjectListData> call, Response<ProjectListData> response) {
//                List<ProjectListData> conList = response.body();
//              Type conListType=new TypeToken<List<Contributor>>(){}.getType();
//                Gson gson=new Gson();
//               String resultString= gson.toJson(conList,conListType);
                Log.d("gaolei", "response.body().size()-------------------" + response.body().data.getSize());
                Log.d("gaolei", "response.body().getCurPage()-------------------" + response.body().data.getCurPage());
                Log.d("gaolei", "response.body().getPageCount()-------------------" + response.body().data.getPageCount());
//                ProjectListData[] myArray = conList.toArray(new ProjectListData[0]);
//                textView.setText(Arrays.toString(myArray));
            }

            @Override
            public void onFailure(Call<ProjectListData> call, Throwable t) {
                textView.setText(t.getLocalizedMessage());
            }
        });
    }


}
