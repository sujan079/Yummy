package com.example.yummy.ui.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.yummy.data.model.CartItem;
import com.example.yummy.data.repository.CartRepository;

import java.util.List;

public class CartItemViewModel extends AndroidViewModel {

    private CartRepository cartRepository;
    public LiveData<List<CartItem>> cartItems;


    public CartItemViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository(application);
    }


    public void getCartItems() {
        cartItems = cartRepository.getCartItems();
    }

    public void updateItem(CartItem cartItem) {
        cartRepository.update(cartItem);
    }

    public void delete(CartItem cartItem) {
        cartRepository.delete(cartItem);
    }
}
