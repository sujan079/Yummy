package com.example.yummy.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.database.DAO.CartDao;
import com.example.yummy.data.database.YummyRoomDatabase;
import com.example.yummy.data.model.CartItem;

import java.util.List;

public class CartRepository {

    private CartDao cartDao;
    private LiveData<List<CartItem>> cartItemLiveData;

    public CartRepository(Application application) {
        YummyRoomDatabase yummyRoomDatabase = YummyRoomDatabase.getInstance(application);

        cartDao = yummyRoomDatabase.cartDao();
        cartItemLiveData = cartDao.getCartItems();

    }

    public LiveData<List<CartItem>> getCartItems() {

        return cartItemLiveData;

    }


    public void insert(CartItem cartItem) {
        new InsertAsyncTask(cartDao).execute(cartItem);
    }

    public void update(CartItem cartItem) {
        new UpdateAsyncTask(cartDao).execute(cartItem);
    }

    public void delete(CartItem cartItem) {
        new DeleteAsyncTask(cartDao).execute(cartItem);
    }

    private static class InsertAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public InsertAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.insert(cartItems[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public UpdateAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.update(cartItems[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public DeleteAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.delete(cartItems[0]);
            return null;
        }
    }





}
