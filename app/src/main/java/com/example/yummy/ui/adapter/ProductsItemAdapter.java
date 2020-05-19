package com.example.yummy.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.Product;
import com.example.yummy.databinding.FoodListItemBinding;

import java.util.ArrayList;

public class ProductsItemAdapter extends RecyclerView.Adapter<ProductsItemAdapter.ProductItemBindingViewHolder> {

    private ArrayList<Product> productArrayList;

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductItemBindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodListItemBinding foodListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.food_list_item, parent, false
        );
        return new ProductItemBindingViewHolder(foodListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemBindingViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        holder.foodListItemBinding.setProduct(product);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductItemBindingViewHolder extends RecyclerView.ViewHolder {
        private FoodListItemBinding foodListItemBinding;

        public ProductItemBindingViewHolder(@NonNull FoodListItemBinding itemView) {
            super(itemView.getRoot());

            foodListItemBinding = itemView;
        }
    }
}
