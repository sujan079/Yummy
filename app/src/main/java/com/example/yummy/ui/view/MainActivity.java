package com.example.yummy.ui.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.yummy.R;
import com.example.yummy.databinding.ActivityMainBinding;
import com.example.yummy.ui.view.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel activityViewModel;

    private ActivityMainBinding activityMainBinding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        activityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        navController = Navigation.findNavController(this, R.id.host_fragment);

        NavigationUI.setupWithNavController(activityMainBinding.bottomNavigationView, navController);

        activityViewModel.showBottomNavigation.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    activityMainBinding.bottomNavigationView.setVisibility(View.VISIBLE);
                } else {
                    activityMainBinding.bottomNavigationView.setVisibility(View.GONE);
                }
            }
        });
    }


}
