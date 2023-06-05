package com.example.reasy;

import java.util.ArrayList;

public class supplier {

    private int supplier_id;
    private String name;


    public supplier(int supplier_id, String name) {
        this.supplier_id = supplier_id;
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public int getId() {
        return supplier_id;
    }
}
