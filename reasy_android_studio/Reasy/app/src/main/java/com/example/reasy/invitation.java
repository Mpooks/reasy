package com.example.reasy;
import java.util.ArrayList;

public class invitation {

    private int reception_id;

    private int customer_id;

    private String date;

    public invitation(int reception_id, int customer_id, String date) {
        this.reception_id = reception_id;
        this.customer_id = customer_id;
        this.date = date;
    }
}
