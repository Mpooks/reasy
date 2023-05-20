package com.example.reasy;

import java.util.ArrayList;

public class user {
    private static String name;
    private int id;
    private double balance;
    private ArrayList<reservation> reservations = new ArrayList<reservation>();

    public user(String name, int id, double balance, ArrayList<reservation> reservations) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.reservations = reservations;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(float balance){
        this.balance=balance;
    }
    public void saveToUser(reservation new_res){
        reservations.add(new_res);
    }
    public ArrayList<reservation> getReservations(){
        return reservations;
    }
    public void updateRes(int res_id,int waiter_id){
        for(int i=0;i<reservations.size();i++){
           int j= (reservations.get(i)).getReservation_id();
           if(res_id==j){
               reservations.get(i).setWaiterToRes(waiter_id);
           }
        }
    }

    public static String getName() {
        return name;
    }
}
