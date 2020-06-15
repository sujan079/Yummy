package com.example.yummy.data.database.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yummy.data.model.CartItem;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void insert(CartItem cartItem);

    @Update
    void update(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);

    @Query("SELECT * FROM cart")
    LiveData<List<CartItem>> getCartItems();

    @Query("SELECT * FROM cart WHERE id=:id")
    CartItem getCartItemById(int id);

    @Query("SELECT TOTAL(price*quantity) from cart")
    Double getTotal();
}
