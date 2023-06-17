package com.example.ex_goods_roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex_goods_roomdb.database.ProductDB;

public class UpdateProductActivity extends AppCompatActivity {
    EditText etProductName, etProductPrice, etProductDescription;
    int productId;
    Button btnUpdateProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_product);

        etProductName = findViewById(R.id.etUpdateName);
        etProductPrice = findViewById(R.id.etUpdatePrice);
        etProductDescription = findViewById(R.id.etUpdateDesc);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);

        etProductName.setText(getIntent().getStringExtra("name"));
        etProductPrice.setText(getIntent().getStringExtra("price"));
        etProductDescription.setText(getIntent().getStringExtra("desc"));
        productId = getIntent().getIntExtra("id", 0);

        btnUpdateProduct.setOnClickListener(v -> {
            try {
                if (
                        etProductName.getText().toString().isEmpty() ||
                                etProductPrice.getText().toString().isEmpty() ||
                                etProductDescription.getText().toString().isEmpty()
                ) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String newName = etProductName.getText().toString();
                String newPrice = etProductPrice.getText().toString();
                String newDesc = etProductDescription.getText().toString();
                ProductDB.getInstance(UpdateProductActivity.this).productDAO().updateProduct(productId, newName, newPrice, newDesc);
                Intent intent = new Intent(UpdateProductActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Update product successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error while updating product", Toast.LENGTH_SHORT).show();
        }});


    }
}