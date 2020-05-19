package com.example.yummy.ui.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.data.model.Product;
import com.example.yummy.data.model.ProductCategory;
import com.example.yummy.data.repository.ProductsRepository;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<ProductCategory>> mProductCategory;
    public MutableLiveData<ArrayList<Product>> mProduct;

    private ProductsRepository productsRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository();
    }

    public void getProductCategory() {
        mProductCategory = productsRepository.getProductCategories();
    }

    public void getProduct() {
        mProduct = productsRepository.getProducts();
    }
}
