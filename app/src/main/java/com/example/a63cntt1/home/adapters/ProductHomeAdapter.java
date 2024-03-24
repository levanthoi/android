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
import com.example.a63cntt1.cart.models.CartProduct;
import com.example.a63cntt1.cart.store.CartManager;
import com.example.a63cntt1.product.models.Product;
import com.example.a63cntt1.utils.ToastUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ViewHolder> {
    private List<Product> products;
    private List<CartProduct> cartProducts;
    private Context ctx;
    private FirebaseAuth fauth;
    public ProductHomeAdapter(Context ctx, List<Product> products) {
        this.products = products;
        this.ctx = ctx;
        fauth = FirebaseAuth.getInstance();
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
                if(fauth.getCurrentUser() != null)
                    handleClick(p);
                else ToastUtil.successToast(ctx, "Chưa đăng nhập !!!");
            }
        });
    }

    private void handleClick(Product p) {

        /** C1: Lưu Cart trên Local */
//        cartProducts = new ArrayList<>();
//        cartProducts.add(new CartProduct(p, 1));
        CartManager.getInstance().addToCart(new CartProduct(p, 1));
        ToastUtil.successToast(ctx, "Thêm " + p.getName() + " thành công");

/**
 * C2: Lưu cart trên firebase
 * */

//        final Map<String, Object> cartMap = new HashMap<>();
//        cartMap.put("name", p.getName());
//        cartMap.put("price", p.getPrice());
//        cartMap.put("image", p.getImage());
//        cartMap.put("quantity", 1);

//        DocumentReference cartRef = FirebaseFirestore.getInstance().collection("cart")
//                .document(fauth.getCurrentUser().getUid())
//                .collection("children").document(Integer.toString(p.getId()));
//
//        cartRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    DocumentSnapshot doc = task.getResult();
//                    if(doc.exists()){
//                        ToastUtil.successToast(ctx, "Sản phẩm đã có trong giỏ hàng");
//                    }else{
//                        cartRef.set(cartMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                ToastUtil.successToast(ctx, "Thêm " + p.getName() + " thành công");
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                ToastUtil.successToast(ctx, "Có lỗi gì đó.");
//                            }
//                        });
//                    }
//                }
//            }
//        });

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
