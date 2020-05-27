package com.example.yummy.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.CartItem;
import com.example.yummy.databinding.CartItemListBinding;

import java.util.ArrayList;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemBindingViewHolder> {

    private ArrayList<CartItem> cartItems;

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartItemBindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemListBinding cartItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cart_item_list, parent, false);
        return new CartItemBindingViewHolder(cartItemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemBindingViewHolder holder, int position) {
        holder.cartItemListBinding.setCartItem(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartItemBindingViewHolder extends RecyclerView.ViewHolder {
        private CartItemListBinding cartItemListBinding;

        public CartItemBindingViewHolder(@NonNull CartItemListBinding itemView) {
            super(itemView.getRoot());
            cartItemListBinding = itemView;

        }
    }

}
