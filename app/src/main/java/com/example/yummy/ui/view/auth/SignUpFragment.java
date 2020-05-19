package com.example.yummy.ui.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.yummy.R;
import com.example.yummy.databinding.FragmentSignUpBinding;
import com.example.yummy.ui.view.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment implements View.OnClickListener {

    private FragmentSignUpBinding fragmentSignUpBinding;

    private AuthViewModel authViewModel;


    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        return fragmentSignUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        fragmentSignUpBinding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        fragmentSignUpBinding.setAuthViewModel(authViewModel);


    }

    public void signUpWithEmailAndPassword(String email, String password) {
        authViewModel.signUpWithEmailPassword(email, password);
        authViewModel.authenticatedUserLiveData.observe(this, authUser -> {
            if (authUser.error != null) {
                makeText(authUser.error);
            } else {
                createUser(authUser.user);

            }
        });


    }

    public void createUser(FirebaseUser user) {
        authViewModel.createUser(user);
        authViewModel.createdUserLiveData.observe(this, created_user -> {
            makeText("User Created");
            goToHome();

        });
    }

    private void makeText(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    private void goToHome() {
        navController.navigate(R.id.action_signUpFragment_to_homeFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                signUpWithEmailAndPassword(authViewModel.email, authViewModel.password);
        }
    }
}