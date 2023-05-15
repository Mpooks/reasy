package com.example.reasy;

import java.util.ArrayList;

public class user {
    private String name;
    private int id;
    private float balance;
    private ArrayList<reservation> reservations = new ArrayList<reservation>();

    public user(String name, int id, float balance, ArrayList<reservation> reservations) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.reservations = reservations;
    }

    public void getBalance(){

    }
    public void setBalance(){

    }
    public void saveToUser(){

    }
    public void getReservations(){

    }
}
