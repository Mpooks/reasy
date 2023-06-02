package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class shop extends user{
    private String address;
    private String city;
    private int capacity;
    private int numofrates;
    private String cuisine_type;
    private double income;
    private double expenses;
    private double goal;
    private double rating;
    private String phone;

    public shop(String email, String password,String name, int id, double balance,String address, String city,int capacity, int numofrates, String cuisine_type, double income, double expenses, double goal, double rating, String phone) {
        super(email,password,name, id, balance);
        this.address = address;
        this.city=city;
        this.capacity = capacity;
        this.numofrates = numofrates;
        this.cuisine_type = cuisine_type;
        this.income = income;
        this.expenses = expenses;
        this.goal = goal;
        this.rating = rating;
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public static ArrayList<product_menu> getShop_m(Context c,int sid) {
        ArrayList<product_menu> pm = new ArrayList<>();
        pm = menu.getMenu(c,sid);
        return pm;
    }

    /*public ArrayList<supply> getSupplyHistory(){
        return supply_history;
    }*/
    public static ArrayList<String> getOpeningHours(Context c, int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchOH(sid);
            ArrayList<String> oh = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    oh.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return oh;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCapacity(){
        return capacity;
    }
    public static ArrayList<table> getTables(Context c, int sid,int cap, String date, String time){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchTables(sid,cap);
            ArrayList<table> tl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    tl.add(new table(cursor.getInt(0),cursor.getInt(1),sid));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            if(tl.isEmpty()){
                return tl;
            }
            else{
                ArrayList<table> tl1 = new ArrayList<>();
                for(table t: tl){
                    ArrayList<reservation> r=new ArrayList<>();
                    r=t.getReservations(c,t.getTable_id(),date,time);
                    if(r.isEmpty()){
                        tl1.add(t);
                    }
                }
                return tl1;
            }
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public String getAddress(){
        return address;
    }
    public static String getCity(Context c,int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            String ad;
            Cursor cursor=dbm.fetchA(sid);
            ad=cursor.getString(0);
            dbm.close();
            return ad;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static double[] getInOutGoal(Context c, int id){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchIOG(id);
            double iog[]=new double[3];
            iog[0]=cursor.getDouble(0);
            iog[1]=cursor.getDouble(1);
            iog[2]=cursor.getDouble(2);

            cursor.close();
            dbm.close();
            return iog;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    /*public void addToSupplyHistory(supply newsupply){
        supply_history.add(newsupply);
    }*/
    public static void changeRating(Context c, int sid, double newr){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchR(sid);
            int n;
            double r,nrat;
            n=cursor.getInt(1);
            r=cursor.getDouble(0);
            double pr_r=n*r;
            n=n+1;
            nrat=(pr_r+newr)/n;
            dbm.updateR(sid,nrat,n);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumofrates() {
        return numofrates;
    }

    public double getRating() {
        return rating;
    }
    public static ArrayList<shop> getShops(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchS();
            ArrayList<shop> sl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    sl.add(new shop(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(0),cursor.getDouble(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9),cursor.getDouble(10),cursor.getDouble(11),cursor.getDouble(12),cursor.getDouble(13),cursor.getString(14)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return sl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
