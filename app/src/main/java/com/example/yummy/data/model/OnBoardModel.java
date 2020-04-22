package com.example.yummy.data.model;

import com.example.yummy.R;

import java.util.ArrayList;

public class OnBoardModel {

    private String title,description;
    private int img_id;

    public OnBoardModel(String title, String description, int img_id) {
        this.title = title;
        this.description = description;
        this.img_id = img_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }


    public static ArrayList<OnBoardModel> getOnBoardItems(){
        return new ArrayList< OnBoardModel>(){{
            add(new OnBoardModel("Choose A Tasty Dish","Order anything you want from your Favorite resturant.", R.drawable.on_board1));
            add(new OnBoardModel("Order","Place your personal order and make your day more delicious.",R.drawable.on_board2));
            add(new OnBoardModel("Pick Up or Delivery","We make food ordering fast,simple and free-no matter if you order online or cash.",R.drawable.on_board3));
        }};
    }
}
