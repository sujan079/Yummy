package com.example.yummy.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.yummy.R;
import com.example.yummy.data.database.DAO.CartDao;
import com.example.yummy.data.model.CartItem;

@Database(entities = {CartItem.class}, exportSchema = false, version = 1)
public abstract class YummyRoomDatabase extends RoomDatabase {

    private static String DATABASE_NAME = "Yummy_DB";
    private static YummyRoomDatabase INSTANCE;


    public static YummyRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, YummyRoomDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }


    public abstract CartDao cartDao();


}
