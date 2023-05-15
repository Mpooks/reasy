package com.example.reasy;
import java.util.ArrayList;

public class invitation {

    private int reception_id;

    private int customer_id;

    private ArrayList<customer> guests = new ArrayList<customer>();

    private String date;

    public invitation(int reception_id, int customer_id, ArrayList<customer> guests, String date) {
        this.reception_id = reception_id;
        this.customer_id = customer_id;
        this.guests = guests;
        this.date = date;
    }
}
