package com.example.yummy.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.ProductCategory;
import com.example.yummy.databinding.HomeCatogeryItemListBinding;

import java.util.ArrayList;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryViewHolderBinding> {


    private ArrayList<ProductCategory> productCategories;


    public void setProductCategories(ArrayList<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    @NonNull
    @Override
    public ProductCategoryViewHolderBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeCatogeryItemListBinding homeCatogeryItemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.home_catogery_item_list, parent, false
        );

        return new ProductCategoryViewHolderBinding(homeCatogeryItemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCategoryViewHolderBinding holder, int position) {
        ProductCategory category = productCategories.get(position);
        holder.homeCatogeryItemListBinding.setCategory(category);
    }

    @Override
    public int getItemCount() {
        return productCategories.size();
    }

    public class ProductCategoryViewHolderBinding extends RecyclerView.ViewHolder {
        private HomeCatogeryItemListBinding homeCatogeryItemListBinding;

        public ProductCategoryViewHolderBinding(@NonNull HomeCatogeryItemListBinding itemView) {
            super(itemView.getRoot());
            homeCatogeryItemListBinding = itemView;
        }
    }
}
