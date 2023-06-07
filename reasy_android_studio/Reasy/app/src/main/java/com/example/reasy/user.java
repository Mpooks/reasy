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

    public user(String email, String password, String name, int id, double balance) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public static ArrayList<user> getUsers(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
        Cursor cursor=dbm.fetchU();
        ArrayList<user> userl = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                userl.add(new user(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(0),cursor.getDouble(4)));
            } while (cursor.moveToNext());
        }

            cursor.close();
            dbm.close();
            return userl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Integer> getReservationsC(Context c,int id){
            ArrayList<reservation> r = new ArrayList<>();
            r=reservation.getReservation(c,id);
            ArrayList<Integer> s=new ArrayList<>();
            for(reservation res:r){
                if(!(s.contains(res.getShop_id()))){
                    s.add(res.getShop_id());
                }
            }
            return s;
    }
    public static double getBalance(Context c,int id){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchB(id);
            double b;
            b=cursor.getDouble(0);
            dbm.close();
            return b;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setBalance(Context c,int id,double cost){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.updateB(id,cost);
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
