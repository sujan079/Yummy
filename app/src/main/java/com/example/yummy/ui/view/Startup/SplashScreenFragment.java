package com.example.yummy.ui.view.Startup;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.yummy.R;
import com.google.firebase.auth.FirebaseAuth;


public class SplashScreenFragment extends Fragment {

    private NavController navController;

    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            navigateTo(R.id.action_splashScreenFragment_to_homeFragment);

        } else {
            navigateTo(R.id.action_splashScreenFragment_to_onBoardFragment);
        }

    }


    public void navigateTo(int destination) {
        int delayTime = 0;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(destination);
            }
        }, delayTime);

    }
}
