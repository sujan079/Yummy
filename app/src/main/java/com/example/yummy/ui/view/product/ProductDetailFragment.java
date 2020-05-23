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

import com.example.yummy.R;
import com.example.yummy.data.model.Product;
import com.example.yummy.databinding.FragmentProductDetailBinding;
import com.example.yummy.ui.utils.Common;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    private Product product;
    private FragmentProductDetailBinding productDetailBinding;


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

        if (getArguments() != null) {
            ProductDetailFragmentArgs args = ProductDetailFragmentArgs.fromBundle(getArguments());

            product = args.getProduct();

            productDetailBinding.setProduct(product);
        }

    }
}
