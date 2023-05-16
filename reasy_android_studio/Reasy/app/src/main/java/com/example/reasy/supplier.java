package com.example.reasy;

import java.util.ArrayList;

public class supplier {

    private int supplier_id;
    private ArrayList<product_supplier> products = new ArrayList<product_supplier>();

    public supplier(int supplier_id, ArrayList<product_supplier> products) {
        this.supplier_id = supplier_id;
        this.products = products;
    }

    public ArrayList<product_supplier> getSupplierProducts(){
        return products;
    }
}
