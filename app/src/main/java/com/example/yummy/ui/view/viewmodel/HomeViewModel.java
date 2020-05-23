package com.example.yummy.ui.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.data.model.Product;
import com.example.yummy.data.model.ProductCategory;
import com.example.yummy.data.repository.ProductsRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeViewModel extends AndroidViewModel {

    public FirebaseUser current_user;

    public MutableLiveData<ArrayList<ProductCategory>> mProductCategory;
    public MutableLiveData<ArrayList<Product>> mProduct;

    private ProductsRepository productsRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository();
        current_user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getProductCategory() {
        mProductCategory = productsRepository.getProductCategories();
    }

    public void getProduct() {
        mProduct = productsRepository.getProducts();
    }

    public ArrayList<String> getGreeting() {

        ArrayList<String> greetingList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String time = "";
        String food_type = "";

        if (timeOfDay >= 0 && timeOfDay < 12) {

            time = ("Good Morning");
            food_type = ("Breakfast");

        } else if (timeOfDay >= 12 && timeOfDay < 16) {

            time = ("Good Afternoon");
            food_type = ("Lunch");

        } else if (timeOfDay >= 16 && timeOfDay < 21) {

            time = ("Good Evening");
            food_type = ("Dinner");

        } else if (timeOfDay >= 21 && timeOfDay < 24) {

            time = ("Good Night");
            food_type = ("Snack");

        }

        greetingList.add(time);
        greetingList.add(food_type);
        return greetingList;
    }



}
