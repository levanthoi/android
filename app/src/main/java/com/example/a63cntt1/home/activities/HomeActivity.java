package com.example.a63cntt1.home.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.example.a63cntt1.R;
import com.example.a63cntt1.home.adapters.ProductHomeAdapter;
import com.example.a63cntt1.product.models.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rcl_product;
    List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        rcl_product = findViewById(R.id.rcl_product);
        getData();
        render();
    }

    private void getData() {
        products = new ArrayList<Product>();
        for(int i=0;i<10;i++){
            int resID = getResId("ss_" + i%6, R.drawable.class);
            Uri imgUri = getUri(resID);
            products.add(new Product(i, "San pham "+ i+1, String.format("%.2f",new Random().nextFloat() * 1000), imgUri ));
        }
    }
    public Uri getUri (int resId){
        return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void render() {
        rcl_product.setAdapter(new ProductHomeAdapter(this, products));
        rcl_product.setLayoutManager(new GridLayoutManager(this, 2));
    }
}