package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class user {
    private String email;
    private String password;
    private String name;
    private int id;
    private double balance;

    private ArrayList<reservation> reservations = new ArrayList<reservation>();

    public user(String email, String password, String name, int id, double balance, ArrayList<reservation> reservations) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(float balance){
        this.balance=balance;
    }
    public void saveToUser(reservation new_res){
        reservations= new ArrayList<>();
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

    public String getName() {
        return name;
    }
    public static ArrayList<user> getUsers(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
        Cursor cursor=dbm.fetch();
        ArrayList<user> userl = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                userl.add(new user(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(0),cursor.getDouble(4),null));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
