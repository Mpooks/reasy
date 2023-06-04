package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class waiter {

    private String name;

    public waiter(String name, int waiter_id, int shop_id) {
        this.name = name;
        this.waiter_id = waiter_id;
        this.shop_id = shop_id;
    }

    private int waiter_id;

    private int shop_id;
    public static ArrayList<waiter> getWaiters(Context c, int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchWaiters(sid);
            ArrayList<waiter> r = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    r.add(new waiter(cursor.getString(1),cursor.getInt(0),cursor.getInt(2)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return r;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getWaiter_id() {
        return waiter_id;
    }
    public static waiter getWD(Context c, int wid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchWD(wid);
            waiter t=new waiter (cursor.getString(1),cursor.getInt(0),cursor.getInt(2));
            cursor.close();
            dbm.close();
            return t;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }
    public static void createAssessment(Context c, int r_id,int w_id,ArrayList<Integer> tid,String date,int w) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.insertAss(r_id,w_id,tid,date,w);
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
