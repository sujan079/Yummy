package com.example.yummy.data.model;

import com.example.yummy.R;

import java.util.ArrayList;

public class ProductCategory {


    private String url;
    private String name;
    private int imgId;

    public ProductCategory() {
    }

    public ProductCategory(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public ProductCategory(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }


    public static ArrayList<ProductCategory> getCategoryData() {
        return new ArrayList<ProductCategory>() {
            {
                add(new ProductCategory("Burger", R.drawable.burger));
                add(new ProductCategory("Cold Drinks", R.drawable.colddrink));
                add(new ProductCategory("Doughnut", R.drawable.doughnut));
                add(new ProductCategory("DrumStick", R.drawable.drumstick));
                add(new ProductCategory("Hot Dog", R.drawable.hotdog));
                add(new ProductCategory("Fries", R.drawable.fries));
                add(new ProductCategory("Ice Cream", R.drawable.icecream));
                add(new ProductCategory("Muffin", R.drawable.muffin));
                add(new ProductCategory("Onion Rings", R.drawable.onionrings));
                add(new ProductCategory("Pizza", R.drawable.pizza));
                add(new ProductCategory("Popcorn", R.drawable.popcorn));
                add(new ProductCategory("Sandwich", R.drawable.sandwich));
                add(new ProductCategory("Subway", R.drawable.subway));
                add(new ProductCategory("Sushi", R.drawable.sushi));
                add(new ProductCategory("Taco", R.drawable.taco));
            }

        };
    }
}
