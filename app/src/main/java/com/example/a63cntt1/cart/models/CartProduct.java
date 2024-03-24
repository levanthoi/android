package com.example.a63cntt1.cart.models;

import com.example.a63cntt1.product.models.Product;

public class CartProduct {
    private Product product;
    private int quantity;

    public CartProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice(){
        return Integer.valueOf(product.getPrice()) * quantity;
    }
}
