package com.example.reasy;

public class product_menu {
    public product_menu(int shop_id, int available_quantity) {
        this.shop_id = shop_id;
        this.available_quantity = available_quantity;
    }

    private int shop_id;
    private int available_quantity;

    public int get_Quantity(){
        return available_quantity;
    }
    public void updateQuantity(int q){
        available_quantity=available_quantity-q;
    }

}
