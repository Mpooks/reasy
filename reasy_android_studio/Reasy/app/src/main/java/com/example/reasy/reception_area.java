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

    public void getAvailability() {}
    public void addtoReceptionArea() {}




}
