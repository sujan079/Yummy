package com.example.yummy.ui.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> showBottomNavigation = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }


}
