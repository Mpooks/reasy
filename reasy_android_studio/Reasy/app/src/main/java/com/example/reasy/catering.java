package com.example.reasy;

public class catering {

    private String name;
    private int catering_id;
    private String cuisine_type;
    private double cost;

    public catering(String name, int catering_id, String cuisine_type, double cost) {
        this.name = name;
        this.catering_id = catering_id;
        this.cuisine_type = cuisine_type;
        this.cost = cost;
    }

    public int getCatering() {
        return catering_id;
    }



}
