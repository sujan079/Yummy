package com.example.yummy.data.repository;


import androidx.lifecycle.MutableLiveData;

import com.example.yummy.data.model.User;
import com.example.yummy.utils.Constants;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection(Constants.FIRESTORE_USERS_COLLECTION);

    public MutableLiveData<User> firebaseSignInWithGoogle(AuthCredential authCredential) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(authTask -> {
            if (authTask.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {

                    User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhotoUrl().toString());
                    user.user = firebaseUser;

                    authenticatedUserMutableLiveData.setValue(user);
                }

            } else {
                User user = new User();
                user.error = authTask.getException().getMessage();
                authenticatedUserMutableLiveData.setValue(user);
            }
        })
                .addOnFailureListener(e -> {
                    User user = new User();
                    user.error = e.getMessage();
                    authenticatedUserMutableLiveData.setValue(user);
                });
        return authenticatedUserMutableLiveData;
    }


    public MutableLiveData<User> firebaseSignUpUserWithEmailAndPassword(String email, String password) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(authTask -> {
            if (authTask.isSuccessful()) {

                if (firebaseAuth != null) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                    User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhotoUrl().toString());
                    user.user = firebaseUser;

                    authenticatedUserMutableLiveData.setValue(user);
                } else {
                    User user = new User();
                    user.error = authTask.getException().getMessage();
                    authenticatedUserMutableLiveData.setValue(user);
                }

            }
        })
                .addOnFailureListener(e -> {
                    User user = new User();
                    user.error = e.getMessage();
                    authenticatedUserMutableLiveData.setValue(user);
                });
        return authenticatedUserMutableLiveData;
    }

    public MutableLiveData<User> firebaseUserSignInWithEmailPassword(String email, String password) {
        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(authTask -> {
                    if (authTask.isSuccessful()) {
                        if (firebaseAuth != null) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhotoUrl().toString());
                            user.user = firebaseUser;
                            authenticatedUserMutableLiveData.setValue(user);
                        } else {
                            User user = new User();
                            user.error = authTask.getException().getMessage();
                            authenticatedUserMutableLiveData.setValue(user);
                        }
                    }
                })
                .addOnFailureListener(e -> {

                    User user = new User();
                    user.error = e.getMessage();
                    authenticatedUserMutableLiveData.setValue(user);
                });
        return authenticatedUserMutableLiveData;
    }

    public MutableLiveData<User> createUserInFirestoreIfNotExists(User authenticatedUser) {
        MutableLiveData<User> newUserMutableLiveData = new MutableLiveData<>();
        DocumentReference uidRef = usersRef.document(authenticatedUser.getUid());
        uidRef.set(authenticatedUser).addOnSuccessListener(aVoid -> {
            newUserMutableLiveData.setValue(authenticatedUser);
        });

        return newUserMutableLiveData;
    }


    }



