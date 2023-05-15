package com.example.reasy;

import java.util.ArrayList;

public class supply {

    public supply(int supply_id, int shop_id, int supplier_id, ArrayList<product_supply> products, String address, boolean sample, float cost) {
        this.supply_id = supply_id;
        this.shop_id = shop_id;
        this.supplier_id = supplier_id;
        this.products = products;
        this.address = address;
        this.sample = sample;
        this.cost = cost;
    }

    private int supply_id;
    private int shop_id;
    private int supplier_id;
    private ArrayList<product_supply> products = new ArrayList<product_supply>();
    private String address;
    private boolean sample;
    private float cost;
}
