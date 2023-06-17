package com.example.ex_goods_roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ex_goods_roomdb.database.ProductDB;
import com.example.ex_goods_roomdb.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Product> productList;
    private ProductDB productDB;
    private RecyclerView productsRV;

    Button addNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productsRV = findViewById(R.id. viewProductsRCV);
//        addNewProduct = (Button) findViewById(R.id.fabAddProduct);

        productList= ProductDB.getInstance(MainActivity.this).productDAO().readProducts();
        productList.add(new Product("product1", "100", "description1"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        productsRV.setLayoutManager(layoutManager);
        productsRV.setAdapter(new ProductAdapter(productList, MainActivity.this));
    }

    public void handleClickAddBtn(View view){
        Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
        startActivity(intent);

    }


}