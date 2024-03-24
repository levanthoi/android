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
import com.example.a63cntt1.cart.models.CartProduct;
import com.example.a63cntt1.cart.store.CartManager;
import com.example.a63cntt1.product.models.Product;

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
//    private List<Product> productList;
    private List<CartProduct> cartProducts;
    private Context ctx;
    private TextView total;


    public CartAdapter(TextView total, List<CartProduct> cartProducts, Context ctx) {
        this.cartProducts = cartProducts;
        this.ctx = ctx;
        this.total = total;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartProduct cartp = cartProducts.get(position);
        Product p = cartp.getProduct();

        Glide.with(ctx).load(p.getImage()).into(holder.idIVSSImage);
        holder.idTVName.setText(p.getName());
        holder.idTVPrice.setText(p.getPrice());
        holder.tvplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuantity(holder, cartp);
            }
        });

        holder.tvminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuantity(holder, cartp);
            }
        });
        holder.tvLineTotal.setText(cartp.getTotalPrice().toString());
    }

    private void decreaseQuantity(ViewHolder holder, CartProduct cartp) {
        if(cartp.getQuantity()>1){
            cartp.setQuantity(cartp.getQuantity() - 1);
            updateQuantity(holder, cartp);
        }
    }

    private void increaseQuantity(ViewHolder holder, CartProduct cartp) {
        cartp.setQuantity(cartp.getQuantity() + 1);
        updateQuantity(holder, cartp);
    }

    private void updateQuantity(ViewHolder holder, CartProduct cartp){
        holder.amount.setText(Integer.toString(cartp.getQuantity()));
        holder.tvLineTotal.setText(Integer.toString(cartp.getTotalPrice()));
        total.setText(CartManager.getInstance().getTotal());
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView idIVSSImage;
        TextView idTVName,idTVPrice, tvLineTotal, tvplus, tvminus, amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idIVSSImage = (ImageView) itemView.findViewById(R.id.idIVSSImage);
            idTVName = (TextView) itemView.findViewById(R.id.idTVName);
            idTVPrice = (TextView) itemView.findViewById(R.id.idTVPrice);
            tvLineTotal = (TextView) itemView.findViewById(R.id.tvLineTotal);
            tvplus = (TextView) itemView.findViewById(R.id.tvplus);
            tvminus = (TextView) itemView.findViewById(R.id.tvminus);
            amount = (TextView) itemView.findViewById(R.id.amount);
        }
    }
}
