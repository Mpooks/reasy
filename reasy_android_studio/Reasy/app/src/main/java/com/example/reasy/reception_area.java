package com.example.reasy;

import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class reception_area {

    private String name;
    private int reception_area_id;
    private  int num_of_guests;
    private double cost;

    public reception_area(String name, int reception_area_id, int num_of_guests, double cost) {
        this.name = name;
        this.reception_area_id = reception_area_id;
        this.num_of_guests = num_of_guests;
        this.cost = cost;
    }

    public String getAvailability(int n,String d) {
        int found = 0;
        if(n<=100){
            if(d.equals("2023-05-23")){
                if(found==0) {
                    return "You are able to book a reception";
                }else {
                    return "Rest booked";
                }
            }else{
                return "Date not valid";
            }
        }else{
            return "People not valid";
        }


    }

    public String getName() {
        return name;
    }

    public int getNum_of_guests() {
        return num_of_guests;
    }

    public int getReception_area_id() {
        return reception_area_id;
    }
}
