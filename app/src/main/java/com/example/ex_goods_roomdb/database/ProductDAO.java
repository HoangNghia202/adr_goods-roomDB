package com.example.ex_goods_roomdb.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ex_goods_roomdb.models.Product;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products")
    List<Product> readProducts();

    @Query("DELETE FROM products WHERE productId = :id")
    void deleteProduct(int id);

    @Query("UPDATE products SET ProductName = :name, ProductDescription = :description, ProductPrice = :price WHERE productId = :id")
    void updateProduct(int id, String name, String description, String price);

    @Query("SELECT * FROM products WHERE productId = :id")
    Product getProduct(int id);

}
