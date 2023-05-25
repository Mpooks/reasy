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
        pm = product_menu.getMenuPr(c,sid);
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
    public static ArrayList<table> getTables(Context c, int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchTables(sid);
            ArrayList<table> tl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    tl.add(new table(cursor.getInt(0),cursor.getInt(1),sid));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return tl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public double[] getInOutGoal(){
        double[] res=new double[3];
        res[0]=income;
        res[1]=expenses;
        res[2]=goal;
        return res;
    }
    /*public void addToSupplyHistory(supply newsupply){
        supply_history.add(newsupply);
    }*/
    public void changeRating(double newr){
        double r=numofrates*rating;
        numofrates++;
        //rating= Double.parseDouble(df.format((r+newr)/numofrates));
        rating = (r+newr)/numofrates;
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
