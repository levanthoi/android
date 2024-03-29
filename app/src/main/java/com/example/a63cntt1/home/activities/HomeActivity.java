package com.example.a63cntt1.home.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a63cntt1.R;
import com.example.a63cntt1.cart.activities.CartActivity;
import com.example.a63cntt1.home.adapters.ProductHomeAdapter;
import com.example.a63cntt1.product.models.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rcl_product;
    List<Product> products;
    FirebaseFirestore db;
    ProductHomeAdapter productAdapter;
    ProgressBar progressBar;
    Button btn_cart;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        rcl_product = findViewById(R.id.rcl_product);
        btn_cart = findViewById(R.id.cart);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        render();

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    private void render() {
//        progressBar.setVisibility(View.VISIBLE);
        rcl_product.setLayoutManager(new GridLayoutManager(this, 2));
        products = new ArrayList<Product>();
        productAdapter = new ProductHomeAdapter(this, products);
        rcl_product.setAdapter(productAdapter);


        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product p = document.toObject(Product.class);
                                products.add(p);
                                productAdapter.notifyDataSetChanged();
                                Log.d(TAG, document.getId() + " => " + p);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi nào đó.", Toast.LENGTH_SHORT).show();
                        }
//                        progressBar.setVisibility(View.GONE);
                    }
                });

//        for(int i=0;i<10;i++){
//            int resID = getResId("ss_" + i%6, R.drawable.class);
//            Uri imgUri = getUri(resID);
//            products.add(new Product(i, "San pham "+ i+1, String.format("%.2f",new Random().nextFloat() * 1000), imgUri ));
//        }

    }
//    public Uri getUri (int resId){
//        return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
//    }
//    public static int getResId(String resName, Class<?> c) {
//
//        try {
//            Field idField = c.getDeclaredField(resName);
//            return idField.getInt(idField);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }

//    private void render() {
//        Toast.makeText(getApplicationContext(), products.toString(), Toast.LENGTH_SHORT).show();
//        rcl_product.setAdapter(new ProductHomeAdapter(this, products));
//        rcl_product.setLayoutManager(new GridLayoutManager(this, 2));
//    }
}