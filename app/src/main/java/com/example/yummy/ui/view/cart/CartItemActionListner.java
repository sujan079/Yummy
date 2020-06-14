package com.example.yummy.ui.view.cart;

import com.example.yummy.data.model.CartItem;

public interface CartItemActionListner {

    void increase(CartItem cartItem);

    void decrease(CartItem cartItem);
}
