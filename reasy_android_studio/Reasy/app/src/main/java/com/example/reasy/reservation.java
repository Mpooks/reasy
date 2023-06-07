package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class reservation {
    private int shop_id;
    private int customer_id;
    private int reservation_id;
    private int num_of_customers;
    private String date;
    private String time;
    private int table_id;
    private String pick_up_address;
    private String requests;

    public reservation(int shop_id, int customer_id, int reservation_id, int num_of_customers, String date, String time, int table_id, String pick_up_address, String requests) {
        this.shop_id = shop_id;
        this.customer_id = customer_id;
        this.reservation_id = reservation_id;
        this.num_of_customers = num_of_customers;
        this.date = date;
        this.time = time;
        this.table_id = table_id;
        this.pick_up_address = pick_up_address;
        this.requests = requests;
    }

    public String getTime(String d){
        String el="different date";
        if(date.compareTo(d)==0) {
            return time;
        }
        else{
            return el;
        }
    }
    public static void createRes(Context c, int sid, int id, int noc, String dateb, String tor, int tid, String address, String req){
        DatabaseManager dbm = new DatabaseManager(c);
        try {
            dbm.open();
            dbm.insertRes(sid, id, noc, dateb, tor, tid, address, req);
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public int getReservation_id(){
        return reservation_id;
    }

    public int getShop_id() {
        return shop_id;
    }
    public static ArrayList<reservation> getReservation(Context c,int cid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchCustRes(cid);
            ArrayList<reservation> r = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    r.add(new reservation(cursor.getInt(1),cursor.getInt(2),cursor.getInt(0),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return r;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static reservation getRD(Context c, int rid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchRD(rid);
            reservation t=new reservation(cursor.getInt(1),cursor.getInt(2),cursor.getInt(0),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8));
            cursor.close();
            dbm.close();
            return t;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNum_of_customers() {
        return num_of_customers;
    }

    public String getRTime() {
        return time;
    }
    public static int getWaiterId(Context c, int rid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchWI(rid);
            int r;
            if (cursor.getCount() > 0) {
                do {
                     r=cursor.getInt(0);
                } while (cursor.moveToNext());
            }
            else{
                r=0;
            }
            cursor.close();
            dbm.close();
            return r;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
