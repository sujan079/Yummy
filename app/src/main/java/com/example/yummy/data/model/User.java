package com.example.yummy.data.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class User implements Serializable {

    public String uid;
    public String name;

    @SuppressWarnings("WeakerAccess")
    public String email;


    @Exclude
    public boolean isCreated, isAuthenticated;

    @Exclude
    public FirebaseUser user;
    @Exclude
    public String error;

    public User() {
    }

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }
}
