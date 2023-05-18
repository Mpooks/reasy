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

    public static String getAvailability(int n,String d) {  //to deutero orisma einai string kai to int d einai gia testing
        int found = 0;
        if(n<=100){
            if(d.equals("2025-02-28")){
                if(found==0) {
                    return "You are able to book";
                }else {
                    return "Rest booked";
                }
            }else{
                return "date not valid";
            }
        }else{
            return "people not valid";
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
