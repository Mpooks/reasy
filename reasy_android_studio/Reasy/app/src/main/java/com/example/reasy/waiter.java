package com.example.reasy;

import java.util.ArrayList;

public class waiter {

    private String name;

    private int waiter_id;

    private int shop_id;

    private ArrayList<String> working_hours = new ArrayList<String>();

    public ArrayList<String> getWaiterHours(){
        return working_hours;
    }
}
