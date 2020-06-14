package com.example.yummy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.CartItem;
import com.example.yummy.databinding.CartItemListBinding;
import com.example.yummy.ui.view.cart.CartItemActionListner;

import java.util.ArrayList;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemBindingViewHolder> {

    private ArrayList<CartItem> cartItems;
    private CartItemActionListner cartItemActionListner;

    public void setCartItemActionListner(CartItemActionListner cartItemActionListner) {
        this.cartItemActionListner = cartItemActionListner;
    }

    public CartItemsAdapter() {
        this.cartItems = new ArrayList<>();
    }

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

    class CartItemBindingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CartItemListBinding cartItemListBinding;

        public CartItemBindingViewHolder(@NonNull CartItemListBinding itemView) {
            super(itemView.getRoot());
            cartItemListBinding = itemView;
            cartItemListBinding.btnAdd.setOnClickListener(this);
            cartItemListBinding.btnRemove.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            CartItem currentItem = cartItems.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.btn_add:
                    currentItem.setQuantity(currentItem.getQuantity() + 1);
                    cartItemActionListner.increase(currentItem);
                    break;
                case R.id.btn_remove:
                    currentItem.setQuantity(currentItem.getQuantity() - 1);
                    cartItemActionListner.decrease(currentItem);
                    break;
            }

        }
    }

}
