package com.example.reasy;

public class product_supply extends product{

    private int supply_id;
    private int quantity;
    public product_supply(int id, String name, double cost, int supply_id, int quantity) {
        super(id,name,cost);
        this.supply_id = supply_id;
        this.quantity = quantity;
    }


}
