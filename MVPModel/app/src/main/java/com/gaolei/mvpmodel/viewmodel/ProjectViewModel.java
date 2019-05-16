package com.gaolei.mvpmodel.viewmodel;

import android.app.Application;

import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.net.RestApiProvider;
import com.gaolei.mvpmodel.net.RestService;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectViewModel extends AndroidViewModel {

    private MutableLiveData<ProjectListData> projectObservable;
    private MutableLiveData<ProjectParams> paramsLiveData;


    private RestService gitHubService = RestApiProvider.getInstance().builder().getApiService();


    public ProjectViewModel(Application application) {
        super(application);

        this.paramsLiveData = new MutableLiveData<>();
        this.projectObservable = new MutableLiveData<>();

//        projectObservable = Transformations.switchMap(paramsLiveData, input -> {
//            ProjectParams params=paramsLiveData.getValue();
//        return projectObservable = new ProjectRepository().getProjectInfo(params.page, params.cid);
//        });

    }
    public void setProjectParams(ProjectParams params) {

        gitHubService.getProjectListData(params.page, params.cid).enqueue(new Callback<ProjectListData>() {
            @Override
            public void onResponse(Call<ProjectListData> call, Response<ProjectListData> response) {
                projectObservable.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ProjectListData> call, Throwable t) {
                projectObservable.setValue(null);
            }


        });
    }
    public LiveData<ProjectListData> getObservableProject() {
        return projectObservable;
    }

    public static class ProjectParams {
        int page = 1, cid = 294;

        public ProjectParams(int page, int cid) {
            this.page = page;
            this.cid = cid;
        }
    }
}
