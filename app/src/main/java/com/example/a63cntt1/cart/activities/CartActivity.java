package com.example.a63cntt1.cart.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a63cntt1.R;
import com.example.a63cntt1.cart.adapters.CartAdapter;
import com.example.a63cntt1.cart.models.CartProduct;
import com.example.a63cntt1.cart.store.CartManager;
import com.example.a63cntt1.product.models.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rcl_cart;
    private TextView total_price;
    private List<Product> products;
    private List<CartProduct> cartProducts;
    private CartAdapter cartAdapter;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();
    }

    private void initView() {
        total_price = findViewById(R.id.total_price);
        cartProducts = CartManager.getInstance().getCartProducts();
        fstore = FirebaseFirestore.getInstance();
        rcl_cart =findViewById(R.id.rcl_cart);
        rcl_cart.setLayoutManager(new LinearLayoutManager(this));
//        cartProducts = new ArrayList<>();
        cartAdapter = new CartAdapter(total_price, cartProducts, this);
        rcl_cart.setAdapter(cartAdapter);

        total_price.setText(CartManager.getInstance().getTotal());
/**
 * C1: Local
 * */
//        localCart();


//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        fstore.collection("cart").document(userId).collection("children").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    products.clear();
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Product p = document.toObject(Product.class);
//                        products.add(p);
//                    }
//                    cartAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi nào đó.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void localCart() {
        cartProducts = CartManager.getInstance().getCartProducts();
        cartAdapter.notifyDataSetChanged();
    }
}