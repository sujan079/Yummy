package com.example.yummy.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.yummy.R;

import java.util.ArrayList;

public class Product implements Parcelable {


    String name;

    String description;

    String url;

    Double price;

    Float rating;

    Integer number_of_reviews;
    int imgId;

    private String time, distance, delivery;





    public Product(Product product){
        this(product.getName(),product.getDescription(),product.getRating(),product.getPrice(),product.getNumber_of_reviews(),product.getImgId(),product.getTime(),product.getDistance(),product.getDelivery());
    }
    public Product(String name, String description, Float rating, double price, Integer number_of_reviews, int imgId, String time, String distance, String delivery) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.number_of_reviews = number_of_reviews;
        this.imgId = imgId;
        this.time = time;
        this.distance = distance;
        this.delivery = delivery;
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
            add(new Product("Taco", "lorem", 3f, 99.99, 200, R.drawable.food2,"12-3pm","12KM","Delivery"));
            add(new Product("Chicken Salad", "lorem", 2f, 120.5, 150, R.drawable.food3,"1-3pm","10KM","No-Delivery"));
            add(new Product("Full BreakFast", "lorem", 4.85f, 250, 100, R.drawable.on_board1,"12-3pm","12KM","Delivery"));
            add(new Product("Lunch Set", "lorem", 4.6f, 150, 100, R.drawable.on_board2,"10-3pm","2KM","No-Delivery"));
            add(new Product("Taco", "lorem", 3f, 99.99, 200, R.drawable.food2,"12-4pm","12KM","Delivery"));
            add(new Product("Chicken Salad", "lorem", 2f, 120.5, 150, R.drawable.food3,"10-3pm","2KM","Delivery"));
            add(new Product("Full BreakFast", "lorem", 4.85f, 250, 100, R.drawable.on_board1,"9-3pm","1KM","No-Delivery"));
            add(new Product("Lunch Set", "lorem", 4.6f, 150, 100, R.drawable.on_board2,"9-5pm","9KM","Delivery"));
            add(new Product("Taco", "lorem", 3f, 99.99, 200, R.drawable.food2,"10-3pm","8KM","Delivery"));
            add(new Product("Chicken Salad", "lorem", 2f, 120.5, 150, R.drawable.food3,"9-3pm","7KM","No-Delivery"));
            add(new Product("Full BreakFast", "lorem", 4.85f, 250, 100, R.drawable.on_board1,"2-3pm","5KM","Delivery"));
            add(new Product("Lunch Set", "lorem", 4.6f, 150, 100, R.drawable.on_board2,"12pm","15KM","Delivery"));
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTime() {
        return time;
    }

    public String getDistance() {
        return distance;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
