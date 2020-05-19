package com.example.yummy.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.yummy.data.model.Product;
import com.example.yummy.data.model.ProductCategory;

import java.util.ArrayList;

public class ProductsRepository {


    public MutableLiveData<ArrayList<ProductCategory>> getProductCategories() {
        MutableLiveData<ArrayList<ProductCategory>> productCategories = new MutableLiveData<>();
        productCategories.setValue(ProductCategory.getCategoryData());
        return productCategories;
    }


    public MutableLiveData<ArrayList<Product>> getProducts() {
        MutableLiveData<ArrayList<Product>> product = new MutableLiveData<>();
        product.setValue(Product.getProducts());
        return product;
    }

}
