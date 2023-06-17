package com.example.ex_goods_roomdb.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private  int productId;
    private String ProductName;
    private String ProductDescription;
    private String ProductPrice;

    public Product(String ProductName, String ProductDescription, String ProductPrice) {
        this.ProductName = ProductName;
        this.ProductDescription = ProductDescription;
        this.ProductPrice = ProductPrice;
    }

    public Product(int id,String ProductName, String ProductDescription, String ProductPrice) {
        this.productId = id;
        this.ProductName = ProductName;
        this.ProductDescription = ProductDescription;
        this.ProductPrice = ProductPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }
}
