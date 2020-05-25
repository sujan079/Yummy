package com.example.yummy.data.model;

import java.util.ArrayList;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static ArrayList<CartItem> getCartItems() {
        return new ArrayList<CartItem>() {{
            for (int i = 0; i < Product.getProducts().size(); i++) {
                add(new CartItem(Product.getProducts().get(i), i));
            }
        }};
    }
}
