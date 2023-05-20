package com.example.reasy;

import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class reception_area {

    private String name;
    private int reception_area_id;
    private static int num_of_guests;
    private static ArrayList<reception> receptions = new ArrayList<reception>();


    private float cost;

    public reception_area(String name, int reception_area_id, int num_of_guests, ArrayList<reception> receptions, float cost) {
        this.name = name;
        this.reception_area_id = reception_area_id;
        this.num_of_guests = num_of_guests;
        this.receptions = receptions;
        this.cost = cost;
    }
    reception_area new_r_a = new reception_area("M1",2,100,receptions,2000);

    public static String getAvailability(int n,String d) {
        int found = 0;
        if(n<=100){
            if(d.equals("2025-02-28")){
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

/*

     for(int i=0;i<receptions.size();i++){
            String r_d=receptions.get(i).getDate();
            if(r_d.compareTo(d)==0){
                found=1;
            }
        }*/

    }
    
    public void addtoReceptionArea(reception new_r) {
        receptions.add(new_r);
    }




}
