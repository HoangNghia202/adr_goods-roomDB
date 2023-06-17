package com.example.ex_goods_roomdb;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex_goods_roomdb.database.ProductDB;
import com.example.ex_goods_roomdb.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = productList.get(position);
        holder.productNameTV.setText(product.getProductName());
        holder.productPriceTV.setText(product.getProductPrice());
        holder.productDescriptionTV.setText(product.getProductDescription());
        holder.btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            ProductDB.getInstance(context).productDAO().deleteProduct(product.getProductId());
                            productList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, productList.size());

                            Toast.makeText(context, "delete successful", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
        holder.btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UpdateProductActivity.class);
                        intent.putExtra("id", product.getProductId());
                        intent.putExtra("name", product.getProductName());
                        intent.putExtra("price", product.getProductPrice());
                        intent.putExtra("desc", product.getProductDescription());
                        context.startActivity(intent);
                    }
                }
        );




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTV, productPriceTV, productDescriptionTV;
        private Button btnDelete, btnUpdate;

        public ViewHolder(View view) {
            super(view);
            productNameTV = view.findViewById(R.id.TVProductName);
            productPriceTV = view.findViewById(R.id.TVProductPrice);
            productDescriptionTV = view.findViewById(R.id.TVProductDesc);
            btnDelete = view.findViewById(R.id.btnDeleteProduct);
            btnUpdate = (Button) view.findViewById(R.id.btnUpdateProduct);
//

        }
    }
}
