package com.example.a63cntt1.cart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a63cntt1.R;
import com.example.a63cntt1.product.models.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Product> productList;
    private Context ctx;

    public CartAdapter(List<Product> productList, Context ctx) {
        this.productList = productList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = productList.get(position);

        Glide.with(ctx).load(p.getImage()).into(holder.idIVSSImage);
        holder.idTVName.setText(p.getName());
        holder.idTVPrice.setText(p.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView idIVSSImage;
        TextView idTVName,idTVPrice, tvLineTotal, tvplus, tvminus;
        EditText amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idIVSSImage = (ImageView) itemView.findViewById(R.id.idIVSSImage);
            idTVName = (TextView) itemView.findViewById(R.id.idTVName);
            idTVPrice = (TextView) itemView.findViewById(R.id.idTVPrice);
            tvLineTotal = (TextView) itemView.findViewById(R.id.tvLineTotal);
            tvplus = (TextView) itemView.findViewById(R.id.tvplus);
            tvminus = (TextView) itemView.findViewById(R.id.tvminus);
            amount = (EditText) itemView.findViewById(R.id.amount);
        }
    }
}
