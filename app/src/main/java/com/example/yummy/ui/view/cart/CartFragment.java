package com.example.yummy.ui.view.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.CartItem;
import com.example.yummy.databinding.FragmentCartBinding;
import com.example.yummy.ui.adapter.CartItemsAdapter;
import com.example.yummy.ui.view.viewmodel.CartItemViewModel;
import com.example.yummy.ui.view.viewmodel.MainActivityViewModel;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    private FragmentCartBinding cartBinding;
    private CartItemViewModel cartItemViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private CartItemsAdapter cartItemsAdapter;
    private RecyclerView.LayoutManager cartLayoutManger;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        cartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return cartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cartItemViewModel = new ViewModelProvider(this).get(CartItemViewModel.class);
        mainActivityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        mainActivityViewModel.showBottomNavigation.setValue(false);
        initCartItems();
    }

    private void initCartItems() {
        cartItemsAdapter = new CartItemsAdapter();
        cartLayoutManger = new LinearLayoutManager(getContext());

        cartBinding.rvCartItems.setAdapter(cartItemsAdapter);
        cartBinding.rvCartItems.setLayoutManager(cartLayoutManger);

        cartItemViewModel.getCartItems();

        cartItemViewModel.cartItems.observe(this, new Observer<ArrayList<CartItem>>() {
            @Override
            public void onChanged(ArrayList<CartItem> cartItems) {
                cartItemsAdapter.setCartItems(cartItems);
                cartItemsAdapter.notifyDataSetChanged();
            }
        });

    }
}
