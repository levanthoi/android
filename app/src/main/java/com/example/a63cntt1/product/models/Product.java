package com.example.a63cntt1.product.models;

import android.net.Uri;

public class Product {
    int id;
    String product_name;
    String product_price;
    Uri image;


    public Product(int id, String product_name, String product_price, Uri image) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
