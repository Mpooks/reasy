package com.example.reasy;

public class product_supplier extends product{

    private int supplier_id;
    private int available_quantity;

    public product_supplier(int id, String name, double cost, int supplier_id, int available_quantity) {
        super(id,name,cost);
        this.supplier_id = supplier_id;
        this.available_quantity = available_quantity;
    }


    public int getQuantity(){
        return available_quantity;
    }
    public void updateQuantity(int q){
        available_quantity=available_quantity-q;
    }
}
