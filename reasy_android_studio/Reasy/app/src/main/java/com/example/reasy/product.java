package com.example.reasy;

public class product {
    public product(int id, String name, double price) {
        this.id=id;
        this.name = name;
        this.price = price;
    }

    private String name;
    private double price;
    private int id;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
