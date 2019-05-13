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
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<ProjectListData> projectObservable;
    private final MutableLiveData<String> projectID;

    public ObservableField<ProjectListData> project = new ObservableField<>();

    public ProjectViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);

        this.projectID = new MutableLiveData<>();

        projectObservable = Transformations.switchMap(projectID, input -> {
            if (input.isEmpty()) {
                Log.i(TAG, "ProjectViewModel projectID is absent!!!");
                return ABSENT;
            }

            Log.i(TAG,"ProjectViewModel projectID is " + projectID.getValue());

            return projectRepository.getProjectInfo(1, 294);
        });
    }

    public LiveData<ProjectListData> getObservableProject() {
        return projectObservable;
    }

    public void setProject(ProjectListData project) {
        this.project.set(project);
    }

    public void setProjectID(String projectID) {
        this.projectID.setValue(projectID);
    }
}
