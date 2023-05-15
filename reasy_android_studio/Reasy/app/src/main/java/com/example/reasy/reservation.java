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
    private int waiter_id;
    private String requests;

    public reservation(int shop_id, int customer_id, int reservation_id, int num_of_customers, String date, String time, int table_id, String pick_up_address, int waiter_id, String requests) {
        this.shop_id = shop_id;
        this.customer_id = customer_id;
        this.reservation_id = reservation_id;
        this.num_of_customers = num_of_customers;
        this.date = date;
        this.time = time;
        this.table_id = table_id;
        this.pick_up_address = pick_up_address;
        this.waiter_id = waiter_id;
        this.requests = requests;
    }

    public void getTime(){

    }
    public void getWaiter(){

    }
    public void setWaiterToRes(){

    }
}
