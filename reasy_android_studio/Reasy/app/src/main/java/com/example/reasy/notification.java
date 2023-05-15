package com.example.reasy;

public class notification {
    private int customer_id;
    private String description;
    private String date;

    public notification(int customer_id, String description, String date) {
        this.customer_id = customer_id;
        this.description = description;
        this.date = date;
    }
}
