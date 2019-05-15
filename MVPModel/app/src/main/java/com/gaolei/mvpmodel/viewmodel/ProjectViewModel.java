package com.gaolei.mvpmodel.viewmodel;

import android.app.Application;
import android.util.Log;

import com.gaolei.mvpmodel.mmodel.ProjectListData;
import com.gaolei.mvpmodel.repository.ProjectRepository;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProjectViewModel extends AndroidViewModel {
    private static final String TAG = ProjectViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();


    private  LiveData<ProjectListData> projectObservable=null;
//    private final MutableLiveData<ProjectParams > paramsLiveData;

    public ObservableField<ProjectListData> project = new ObservableField<>();

    public ProjectViewModel( Application application) {
        super(application);

//        this.paramsLiveData = new MutableLiveData<>();
//
//        projectObservable = Transformations.switchMap(paramsLiveData, input -> {
//            if (paramsLiveData==null) {
//                Log.i(TAG, "ProjectViewModel projectID is absent!!!");
//                return ABSENT;
//            }
//
//            Log.i(TAG, "ProjectViewMosetProjectdel projectID is " + paramsLiveData.getValue());
//            ProjectParams params=paramsLiveData.getValue();

        projectObservable = new ProjectRepository().getProjectInfo(1, 294);
//        });
    }

    public LiveData<ProjectListData> getObservableProject() {
        return projectObservable;
    }

    public void setProject(ProjectListData project) {
        this.project.set(project);
    }
//
//    public void setProjectParams(ProjectParams params) {
//        this.paramsLiveData.setValue(params);
//    }
//
//    public static class ProjectParams {
//        int page = 1, cid = 294;
//
//        public ProjectParams(int page, int cid) {
//            this.page = page;
//            this.cid = cid;
//        }
//    }
}
