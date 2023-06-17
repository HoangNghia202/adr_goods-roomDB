package com.example.ex_goods_roomdb.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {com.example.ex_goods_roomdb.models.Product.class}, version = 1)
public abstract class ProductDB extends RoomDatabase {
    private  static final String  DBName="product.db";
    private  static ProductDB instance;
    public static synchronized ProductDB getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),ProductDB.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public  abstract ProductDAO productDAO();
}
