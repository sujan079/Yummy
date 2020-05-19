package com.example.yummy.data.model;

import com.example.yummy.R;

import java.util.ArrayList;

public class Product {

    String name, description, url;
    Double price;
    Float rating;

    Integer number_of_reviews;
    int imgId;

    public Product() {
    }

    ;

    public Product(String name, String description, Float rating, double price, Integer number_of_reviews, int imgId) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.number_of_reviews = number_of_reviews;
        this.imgId = imgId;
    }

    public Product(String name, String description, String url, Float rating, double price, Integer number_of_reviews) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.rating = rating;
        this.price = price;
        this.number_of_reviews = number_of_reviews;
    }

    public static ArrayList<Product> getProducts() {
        return new ArrayList<Product>() {{
            add(new Product("taco", "lorem", 5f, 99.99, 200, R.drawable.food2));
            add(new Product("Chicken Salad", "lorem", 5f, 120.5, 150, R.drawable.food3));
            add(new Product("Full BreakFast", "lorem", 4.85f, 250, 100, R.drawable.on_board1));
            add(new Product("Lunch Set", "lorem", 4.6f, 150, 100, R.drawable.on_board2));
        }};
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Float getRating() {
        return rating;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumber_of_reviews() {
        return number_of_reviews;
    }

    public int getImgId() {
        return imgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNumber_of_reviews(Integer number_of_reviews) {
        this.number_of_reviews = number_of_reviews;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
