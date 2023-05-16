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

    public float getBalance(){
        return balance;
    }
    public void setBalance(float balance){
        this.balance=balance;
    }
    public void saveToUser(reservation newres){
        reservations.add(newres);
    }
    public ArrayList<reservation> getReservations(){
        return reservations;
    }
    public void updateRes(int resid){
        for(int i=0;i<reservations.size();i++){
           int j= (reservations.get(i)).getReservation_id();
           if(resid==j){
               
           }
        }
    }
}
