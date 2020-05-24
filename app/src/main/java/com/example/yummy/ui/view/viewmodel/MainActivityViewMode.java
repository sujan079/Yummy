package com.example.yummy.ui.view.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityViewMode extends AndroidViewModel {
    public MutableLiveData<Boolean> show_bottom_navigation;

    public MainActivityViewMode(@NonNull Application application) {
        super(application);
    }

    public void showBottomNav(BottomNavigationView view) {
        view.setVisibility(View.INVISIBLE);
    }

    public void hideBottomNav(BottomNavigationView view) {
        view.setVisibility(View.VISIBLE);

    }
}
