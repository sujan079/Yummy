package com.example.yummy.ui.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.databinding.FragmentHomeBinding;
import com.example.yummy.ui.adapter.ProductCategoryAdapter;
import com.example.yummy.ui.adapter.ProductsItemAdapter;
import com.example.yummy.ui.view.viewmodel.HomeViewModel;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding fragmentHomeBinding;
    private HomeViewModel homeViewModel;

    private ProductCategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager categoryLayoutManager;

    private ProductsItemAdapter productsItemAdapter;
    private RecyclerView.LayoutManager productLayoutManger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initProductCategory();
        initProduct();

    }

    public void initProductCategory() {
        categoryAdapter = new ProductCategoryAdapter();
        categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        fragmentHomeBinding.rvProductCategory.setAdapter(categoryAdapter);
        fragmentHomeBinding.rvProductCategory.setLayoutManager(categoryLayoutManager);

        homeViewModel.getProductCategory();
        homeViewModel.mProductCategory.observe(this, categories -> {
            categoryAdapter.setProductCategories(categories);
            categoryAdapter.notifyDataSetChanged();
        });

    }

    public void initProduct() {
        productsItemAdapter = new ProductsItemAdapter();
        productLayoutManger = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);

        fragmentHomeBinding.rvProductList.setAdapter(productsItemAdapter);
        fragmentHomeBinding.rvProductList.setLayoutManager(productLayoutManger);

        homeViewModel.getProduct();

        homeViewModel.mProduct.observe(this, products -> {

            productsItemAdapter.setProductArrayList(products);
            productsItemAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}
