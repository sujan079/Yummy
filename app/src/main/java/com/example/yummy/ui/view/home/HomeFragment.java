package com.example.yummy.ui.view.home;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.Product;
import com.example.yummy.databinding.FragmentHomeBinding;
import com.example.yummy.ui.adapter.ProductCategoryAdapter;
import com.example.yummy.ui.adapter.ProductsItemAdapter;
import com.example.yummy.ui.utils.Common;
import com.example.yummy.ui.view.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment implements HomeActionListener {


    private FragmentHomeBinding fragmentHomeBinding;
    private HomeViewModel homeViewModel;

    private ProductCategoryAdapter categoryAdapter;
    private RecyclerView.LayoutManager categoryLayoutManager;

    private ProductsItemAdapter productsItemAdapter, bestFoodAdapter;
    private RecyclerView.LayoutManager productLayoutManger, bestProductLayoutManger;

    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Common.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), true);

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

        navController = Navigation.findNavController(Objects.requireNonNull(getView()));

        initGreetingData();
        initProductCategory();
        initProduct();
        initBestProduct();

    }

    public void initGreetingData() {
        ArrayList<String> greetingInfo = homeViewModel.getGreeting();
        fragmentHomeBinding.tvGreeting.setText(greetingInfo.get(0));
        fragmentHomeBinding.tvFoodType.setText("What " + greetingInfo.get(1) + " would you like to have? ");
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
        productsItemAdapter = new ProductsItemAdapter(false, this);
        productLayoutManger = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);

        fragmentHomeBinding.rvProductList.setAdapter(productsItemAdapter);
        fragmentHomeBinding.rvProductList.setLayoutManager(productLayoutManger);

        homeViewModel.getProduct();

        homeViewModel.mProduct.observe(this, products -> {

            productsItemAdapter.setProductArrayList(products);
            productsItemAdapter.notifyDataSetChanged();
        });
    }

    public void initBestProduct() {
        bestFoodAdapter = new ProductsItemAdapter(true, this);
        bestProductLayoutManger = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        fragmentHomeBinding.rvBestFood.setAdapter(bestFoodAdapter);
        fragmentHomeBinding.rvBestFood.setLayoutManager(bestProductLayoutManger);

        homeViewModel.getProduct();

        homeViewModel.mProduct.observe(this, products -> {

            bestFoodAdapter.setProductArrayList(products);
            bestFoodAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void itemClicked(Product product) {
        HomeFragmentDirections.ActionHomeFragmentToProductDetailFragment action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product);
        navController.navigate(action);
    }
}
