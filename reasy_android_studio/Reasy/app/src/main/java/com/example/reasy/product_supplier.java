package com.example.reasy;

public class product_supplier {

    public product_supplier(int supplier_id, int available_quantity) {
        this.supplier_id = supplier_id;
        this.available_quantity = available_quantity;
    }

    private int supplier_id;
    private int available_quantity;

    public int getQuantity(){
        return available_quantity;
    }
    public void updateQuantity(int q){
        available_quantity=available_quantity-q;
    }
}
