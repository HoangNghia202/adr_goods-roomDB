package com.example.ex_goods_roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ex_goods_roomdb.database.ProductDB;
import com.example.ex_goods_roomdb.models.Product;

public class AddProductActivity extends AppCompatActivity {
    EditText etProductName, etProductPrice, etProductDescription;
    Button btnAddProduct;
    int productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = findViewById(R.id.etAddName);
        etProductPrice = findViewById(R.id.etAddPrice);
        etProductDescription = findViewById(R.id.etAddDesc);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        btnAddProduct.setOnClickListener(v->{
            try {
                if (
                        etProductName.getText().toString().isEmpty() ||
                                etProductPrice.getText().toString().isEmpty() ||
                                etProductDescription.getText().toString().isEmpty()
                ) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkIsNumber(etProductPrice.getText().toString())) {
                    Toast.makeText(this, "Price must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                String newName = etProductName.getText().toString();
                String newPrice = etProductPrice.getText().toString();
                String newDesc = etProductDescription.getText().toString();
ProductDB.getInstance(AddProductActivity.this).productDAO().insertProduct(new Product(newName, newPrice, newDesc));
                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
                Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Error while adding product", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkIsNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}