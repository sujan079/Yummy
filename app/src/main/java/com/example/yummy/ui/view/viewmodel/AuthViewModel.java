package com.example.yummy.ui.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.yummy.data.model.User;
import com.example.yummy.data.repository.AuthRepository;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    public LiveData<User> createdUserLiveData;
    private AuthRepository authRepository;

    public LiveData<User> authenticatedUserLiveData;


    public String email, password, password2;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository();

    }


    public void signInWithGoogle(AuthCredential authCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(authCredential);
    }

    public void signInWithEmailPassword(String email, String password) {
        authenticatedUserLiveData = authRepository.firebaseUserSignInWithEmailPassword(email, password);
    }

    public void signUpWithEmailPassword(String email, String password) {
        authenticatedUserLiveData = authRepository.firebaseSignUpUserWithEmailAndPassword(email, password);

    }


    public void createUser(FirebaseUser authenticatedUser) {
        User user = new User(authenticatedUser.getUid(), authenticatedUser.getDisplayName(), authenticatedUser.getEmail());
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(user);
    }


}
