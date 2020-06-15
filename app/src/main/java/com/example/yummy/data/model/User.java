package com.example.yummy.data.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class User  {

    private String uid;

    private String username;
    private String email;
    private String imageUrl;
    private String phone;



    @Exclude
    public FirebaseUser user;

    @Exclude
    public String error;

    public User() {
    }

    public User(String uid, String name, String email, String imageUrl) {
        this.uid = uid;
        this.username = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.phone = "";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
