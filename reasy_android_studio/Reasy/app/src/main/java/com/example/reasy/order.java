package com.example.reasy;

import java.util.ArrayList;

public class order {
    private int customer_id;
    private int order_id;
    private int shop_id;
    private double cost;
    private String order_method;
    private String payment_method;
    private ArrayList<product_order> products=new ArrayList<product_order>();

    public order(int customer_id, int order_id, int shop_id, double cost, String order_method, String payment_method, ArrayList<product_order> products) {
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.shop_id = shop_id;
        this.cost = cost;
        this.order_method = order_method;
        this.payment_method = payment_method;
        this.products = products;
    }

    public ArrayList<product_order> getProducts() {
        return products;
    }

}
