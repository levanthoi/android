package com.example.a63cntt1.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a63cntt1.R;
import com.example.a63cntt1.product.models.Product;

import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ViewHolder> {
    private List<Product> products;
    private Context ctx;
    public ProductHomeAdapter(Context ctx, List<Product> products) {
        this.products = products;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  Product p = products.get(position);
        holder.product_name.setText(p.getProduct_name());
        holder.product_price.setText(p.getProduct_price());
        holder.product_image.setImageURI(p.getImage());

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(p);
            }
        });
    }

    private void handleClick(Product p) {
        Toast.makeText(ctx, "Thêm "+p.getProduct_name() + " thành công !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView product_name, product_price;
        public ImageView product_image, btnAdd;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            btnAdd = (ImageView) itemView.findViewById(R.id.btnAdd);
        }
    }
}
