package com.example.reasy;

import java.util.ArrayList;

public class reception_area {
    private String name;
    private int reception_area_id;
    private int num_of_guests;
    private ArrayList<reception> receptions = new ArrayList<reception>();
    private float cost;

    public reception_area(String name, int reception_area_id, int num_of_guests, ArrayList<reception> receptions, float cost) {
        this.name = name;
        this.reception_area_id = reception_area_id;
        this.num_of_guests = num_of_guests;
        this.receptions = receptions;
        this.cost = cost;
    }

    public int getAvailability(int n,String d) {
        int found=0;
        if(n>num_of_guests){
            found=1;
        }
        for(int i=0;i<receptions.size();i++){
            String r_d=receptions.get(i).getDate();
            if(r_d.compareTo(d)==0){
                found=1;
            }
        }
        return found;
    }
    public void addtoReceptionArea(reception new_r) {
        receptions.add(new_r);
    }




}
