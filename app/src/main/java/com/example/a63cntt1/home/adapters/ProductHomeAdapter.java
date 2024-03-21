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

import com.bumptech.glide.Glide;
import com.example.a63cntt1.R;
import com.example.a63cntt1.product.models.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ViewHolder> {
    private List<Product> products;
    private Context ctx;
    private FirebaseAuth fauth;
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
        holder.product_name.setText(p.getName());
        holder.product_price.setText(p.getPrice());
        Glide.with(ctx).load(p.getImage()).into(holder.product_image);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(p);
            }
        });
    }

    private void handleClick(Product p) {
        fauth = FirebaseAuth.getInstance();
        final Map<String, Object> cartMap = new HashMap<>();
        cartMap.put("name", p.getName());
        cartMap.put("price", p.getPrice());
        cartMap.put("image", p.getImage());

        DocumentReference cartRef = FirebaseFirestore.getInstance().collection("cart").document(fauth.getCurrentUser().getUid());

        cartRef.set(cartMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ctx, "Thêm "+p.getName() + " thành công !!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ctx, "Có lỗi xảy ra !!!", Toast.LENGTH_SHORT).show();
            }
        });

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
