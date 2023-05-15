package com.example.reasy;

import java.util.ArrayList;

public class menu {
    private int shop_id;
    private ArrayList<product_menu> products=new ArrayList<product_menu>();
    private float rating;
    public menu(int shop_id, ArrayList<product_menu> products, float rating) {
        this.shop_id = shop_id;
        this.products = products;
        this.rating = rating;
    }

    public void getProducts(){

    }
    public void changeRatingMenu(){

    }
}
