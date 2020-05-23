package com.example.yummy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.model.Product;
import com.example.yummy.databinding.FoodHorizontalItemListBinding;
import com.example.yummy.databinding.FoodListItemBinding;
import com.example.yummy.ui.view.home.HomeActionListener;

import java.util.ArrayList;

public class ProductsItemAdapter extends RecyclerView.Adapter<ProductsItemAdapter.ProductItemBindingViewHolder> {

    private ArrayList<Product> productArrayList;
    private boolean isHorizontal;

    private HomeActionListener homeActionListener;



    public ProductsItemAdapter(boolean isHorizontal, HomeActionListener actionListener) {
        this.isHorizontal = isHorizontal;
        homeActionListener = actionListener;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductItemBindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (isHorizontal) {
            FoodHorizontalItemListBinding foodHorizontalItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.food_horizontal_item_list, parent, false);

            return new ProductItemBindingViewHolder(foodHorizontalItemListBinding);
        }

        FoodListItemBinding foodListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.food_list_item, parent, false
        );
        return new ProductItemBindingViewHolder(foodListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemBindingViewHolder holder, int position) {

        Product product = productArrayList.get(position);

        if (isHorizontal) {
            holder.foodHorizontalItemListBinding.setProduct(product);
        } else {
            holder.foodListItemBinding.setProduct(product);
        }

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductItemBindingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private FoodListItemBinding foodListItemBinding;
        private FoodHorizontalItemListBinding foodHorizontalItemListBinding;

        public ProductItemBindingViewHolder(@NonNull FoodListItemBinding itemView) {
            super(itemView.getRoot());

            foodListItemBinding = itemView;
            foodListItemBinding.getRoot().setOnClickListener(this);

        }

        public ProductItemBindingViewHolder(@NonNull FoodHorizontalItemListBinding itemView) {
            super(itemView.getRoot());

            foodHorizontalItemListBinding = itemView;
            foodHorizontalItemListBinding.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            homeActionListener.itemClicked(productArrayList.get(getAdapterPosition()));
        }
    }
}
