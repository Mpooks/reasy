package com.example.reasy;

import java.util.ArrayList;

public class supplier {

    private int supplier_id;

    public supplier(int supplier_id, String name, ArrayList<product_supplier> products) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.products = products;
    }

    private String name;
    private ArrayList<product_supplier> products = new ArrayList<product_supplier>();



    public ArrayList<product_supplier> getSupplierProducts(){
        return products;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return supplier_id;
    }
}
