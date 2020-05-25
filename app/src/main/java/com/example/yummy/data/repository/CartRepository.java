package com.example.yummy.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.yummy.data.model.CartItem;

import java.util.ArrayList;

public class CartRepository {

    public MutableLiveData<ArrayList<CartItem>> getCartItems() {
        MutableLiveData<ArrayList<CartItem>> cartItemsMutableLiveData = new MutableLiveData<>();
        cartItemsMutableLiveData.setValue(CartItem.getCartItems());
        return cartItemsMutableLiveData;
    }
}
