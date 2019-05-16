package com.gaolei.mvpmodel.viewmodel;

import android.app.Application;

import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.net.RestApiProvider;
import com.gaolei.mvpmodel.net.RestService;
import com.gaolei.mvpmodel.repository.ProjectRepository;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectViewModel extends AndroidViewModel {
    private static final String TAG = ProjectViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();


    private  LiveData<ProjectListData> projectObservable=new MutableLiveMutableLiveDataData();
    private  MutableLiveData<ProjectParams > paramsLiveData;

    public ObservableField<ProjectListData> project = new ObservableField<>();

    private RestService gitHubService = RestApiProvider.getInstance().builder().getApiService();


    public ProjectViewModel( Application application) {
        super(application);

        this.paramsLiveData = new MutableLiveData<>();

        projectObservable = Transformations.switchMap(paramsLiveData, input -> {
            if (paramsLiveData==null) {
                return ABSENT;
            }
        return projectObservable = new ProjectRepository().getProjectInfo(1, 294);
        });
            ProjectParams params=paramsLiveData.getValue();


    }

    public LiveData<ProjectListData> getObservableProject() {
        return projectObservable;
    }

    public void setProject(ProjectListData project) {
        this.project.set(project);
    }
//
    public void setProjectParams(ProjectParams params) {
        //this.paramsLiveData.setValue(params);

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

    public static class ProjectParams {
        int page = 1, cid = 294;

        public ProjectParams(int page, int cid) {
            this.page = page;
            this.cid = cid;
        }
    }
}
