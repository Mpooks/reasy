package com.example.reasy;

public class reservation {
    private int shop_id;
    private int customer_id;
    private int reservation_id;
    private int num_of_customers;
    private String date;
    private String time;
    private int table_id;
    private String pick_up_address;
    private String requests;

    public reservation(int shop_id, int customer_id, int reservation_id, int num_of_customers, String date, String time, int table_id, String pick_up_address, String requests) {
        this.shop_id = shop_id;
        this.customer_id = customer_id;
        this.reservation_id = reservation_id;
        this.num_of_customers = num_of_customers;
        this.date = date;
        this.time = time;
        this.table_id = table_id;
        this.pick_up_address = pick_up_address;
        this.requests = requests;
    }

    public String getTime(String d){
        String el="different date";
        if(date.compareTo(d)==0) {
            return time;
        }
        else{
            return el;
        }
    }
    /*public int getWaiter(){
       return waiter_id;
    }
    public void setWaiterToRes(int waiter_id){
        this.waiter_id=waiter_id;
    }*/
    public int getReservation_id(){
        return reservation_id;
    }

    public int getShop_id() {
        return shop_id;
    }
}
