package com.example.reasy;


public class supply {

    private int supply_id;
    private int shop_id;
    private int supplier_id;
    private String address;
    private boolean sample;
    private double cost;

    public supply(int supply_id, int shop_id, int supplier_id, String address, boolean sample, double cost) {
        this.supply_id = supply_id;
        this.shop_id = shop_id;
        this.supplier_id = supplier_id;
        this.address = address;
        this.sample = sample;
        this.cost = cost;
    }


}
