package com.example.yummy.ui.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.yummy.R;
import com.example.yummy.databinding.FragmentGetStartedBinding;
import com.example.yummy.ui.utils.Common;
import com.example.yummy.ui.view.viewmodel.AuthViewModel;
import com.example.yummy.utils.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class GetStartedFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private FragmentGetStartedBinding getStartedBinding;
    private AuthViewModel authViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Common.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), true);

        getStartedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_started, container, false);

        initButtonOnClicks();

        return getStartedBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        getStartedBinding.setAuthViewModel(authViewModel);

    }


    private void initButtonOnClicks() {
        getStartedBinding.btnGoogleSignUp.setOnClickListener(this);
        getStartedBinding.btnSignIn.setOnClickListener(this);
        getStartedBinding.btnSignUp.setOnClickListener(this);
    }

    private GoogleSignInClient initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        return GoogleSignIn.getClient(getContext(), googleSignInOptions);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                if (googleSignInAccount != null) {
                    getGoogleAuthCredential(googleSignInAccount);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }

    }


    private void getGoogleAuthCredential(GoogleSignInAccount googleSignInAccount) {
        String googleTokenId = googleSignInAccount.getIdToken();
        AuthCredential googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null);
        signInWithGoogleAuthCredential(googleAuthCredential);

    }

    private void signInWithGoogleAuthCredential(AuthCredential googleAuthCredential) {
        authViewModel.signInWithGoogle(googleAuthCredential);

        authViewModel.authenticatedUserLiveData.observe(this, authUser -> {
            if (authUser.error != null) {
                makeText(authUser.error);
            } else {
                createUser(authUser.user);

            }
        });
    }


    private void createUser(FirebaseUser authenticatedUser) {
        authViewModel.createUser(authenticatedUser);
        authViewModel.createdUserLiveData.observe(this, user -> {
            makeText("User Created");
            goToHome();
        });
    }

    public void signIn() {

        Intent signInIntent = initGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, Constants.REQUEST_GOOGLE_SIGN);

    }


    private void goToHome() {
        navController.navigate(R.id.action_getStartedFragment_to_homeFragment);
    }

    private void makeText(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                navController.navigate(R.id.action_getStartedFragment_to_signUpFragment);
                break;
            case R.id.btn_sign_in:
                navController.navigate(R.id.action_getStartedFragment_to_signInFragment);
                break;
            case R.id.btn_google_sign_up:
                signIn();
        }
    }
}
