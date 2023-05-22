package com.example.reasy;

public class product_order extends product{
    public product_order(int id,String name, double cost, int order_id, int quantity) {
        super(id,name,cost);
        this.order_id = order_id;
        this.quantity = quantity;
    }

    private int order_id;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }
}
