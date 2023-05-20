package com.example.reasy;

import java.util.ArrayList;

public class waiter {

    private String name;

    public waiter(String name, int waiter_id, int shop_id, ArrayList<String> working_hours) {
        this.name = name;
        this.waiter_id = waiter_id;
        this.shop_id = shop_id;
        this.working_hours = working_hours;
    }

    private int waiter_id;

    private int shop_id;

    private ArrayList<String> working_hours = new ArrayList<String>();

    public ArrayList<String> getWaiterHours(){
        return working_hours;
    }
}
