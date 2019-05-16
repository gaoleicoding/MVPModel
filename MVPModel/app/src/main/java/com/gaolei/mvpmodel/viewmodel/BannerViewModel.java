package com.gaolei.mvpmodel.viewmodel;

import android.app.Application;

import com.gaolei.mvpmodel.mmodel.BannerListData;
import com.gaolei.mvpmodel.repository.BannerRepository;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BannerViewModel extends AndroidViewModel {
    private static final String TAG = BannerViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();


    private LiveData<BannerListData> projectObservable = null;

    public ObservableField<BannerListData> project = new ObservableField<>();

    public BannerViewModel(Application application) {
        super(application);

        projectObservable = new BannerRepository().getBanner();
    }

    public LiveData<BannerListData> getObservableProject() {
        return projectObservable;
    }

}
