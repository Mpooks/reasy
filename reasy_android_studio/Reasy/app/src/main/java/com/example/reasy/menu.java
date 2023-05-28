package com.example.reasy;

import android.content.Context;

import java.util.ArrayList;

public class menu {
    private int shop_id;
    private ArrayList<product_menu> products=new ArrayList<product_menu>();
    private double rating;
    private int num_of_rates;
    public menu(int shop_id, ArrayList<product_menu> products, double rating, int num_of_rates) {
        this.shop_id = shop_id;
        this.products = products;
        this.rating = rating;
        this.num_of_rates=num_of_rates;
    }

    public ArrayList<product_menu> getProducts(){
        return products;
    }
    public void changeRatingMenu(double new_r){
        double r=num_of_rates*rating;
        num_of_rates++;
        rating=(rating+new_r)/num_of_rates;
    }
    public static ArrayList<product_menu> getMenu(Context c, int sid) {
        ArrayList<product_menu> pm = new ArrayList<>();
        pm = product_menu.getMenuPr(c,sid);
        return pm;
    }
}
