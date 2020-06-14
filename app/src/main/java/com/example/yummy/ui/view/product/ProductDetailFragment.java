package com.example.yummy.ui.view.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.yummy.R;
import com.example.yummy.data.model.CartItem;
import com.example.yummy.data.model.Product;
import com.example.yummy.data.repository.CartRepository;
import com.example.yummy.databinding.FragmentProductDetailBinding;
import com.example.yummy.ui.utils.Common;
import com.example.yummy.ui.view.viewmodel.MainActivityViewModel;


public class ProductDetailFragment extends Fragment implements View.OnClickListener {

    private Product product;
    private FragmentProductDetailBinding productDetailBinding;
    private CartRepository cartRepository;

    private MainActivityViewModel activityViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Common.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), true);

        productDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        return productDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartRepository = new CartRepository(getActivity().getApplication());

        activityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        activityViewModel.showBottomNavigation.setValue(false);

        if (getArguments() != null) {
            ProductDetailFragmentArgs args = ProductDetailFragmentArgs.fromBundle(getArguments());

            product = args.getProduct();

            productDetailBinding.setProduct(product);
        }

        productDetailBinding.btnAddToBag.setOnClickListener(this);
        productDetailBinding.ivAddToBag.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_to_bag:
                addToBag();
                break;
            case R.id.iv_add_to_bag:
                addToBag();
                break;

        }
    }

    void addToBag() {
        CartItem cartItem = new CartItem(product.getName(), product.getPrice(), 1, product.getUrl());
        cartRepository.insert(cartItem);
    }
}
