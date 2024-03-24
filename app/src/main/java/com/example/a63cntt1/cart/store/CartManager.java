package com.example.a63cntt1.cart.store;

import com.example.a63cntt1.cart.models.CartProduct;
import com.example.a63cntt1.product.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartProduct> cartProductList;

    public CartManager() {
        cartProductList = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<CartProduct> getCartProducts() {
        return cartProductList;
    }

    public void addToCart(CartProduct product) {
        cartProductList.add(product);
    }

    public String getTotal(){
        int total = 0;
        for(CartProduct cartp : cartProductList){
            total += cartp.getTotalPrice();
        }
        return Integer.toString(total);
    }
}
