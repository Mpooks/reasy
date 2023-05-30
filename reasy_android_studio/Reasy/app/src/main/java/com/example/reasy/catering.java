package com.example.reasy;

public class catering {

    private String name;
    private int catering_id;
    private String cuisine_type;
    private double cost;

    public catering(int catering_id, String name, double cost, String cuisine_type) {
        this.name = name;
        this.catering_id = catering_id;
        this.cuisine_type = cuisine_type;
        this.cost = cost;
    }

    public int getCatering() {
        return catering_id;
    }

    public String getCuisineType() {
        return cuisine_type;
    }

    public String getName() {
        return name;
    }
}
