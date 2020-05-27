package com.example.yummy.ui.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.data.model.CartItem;
import com.example.yummy.data.repository.CartRepository;

import java.util.ArrayList;

public class CartItemViewModel extends AndroidViewModel {

    private CartRepository cartRepository;
    public MutableLiveData<ArrayList<CartItem>> cartItems;


    public CartItemViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository();

    }


    public void getCartItems() {
        cartItems = cartRepository.getCartItems();
    }
}
