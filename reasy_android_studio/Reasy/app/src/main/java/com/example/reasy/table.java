package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class table {

    private int table_id;

    private int capacity;

    private int shop_id;


    public table(int table_id, int capacity, int shop_id) {
        this.table_id = table_id;
        this.capacity = capacity;
        this.shop_id = shop_id;
    }

    public int getCapacity(){
        return capacity;
    }
    public int getTable_id(){
        return table_id;
    }

    public static ArrayList<reservation> getReservations(Context c, int tid,String dateb, String tor){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchRes(tid);
            ArrayList<reservation> r = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    r.add(new reservation(cursor.getInt(1),cursor.getInt(2),cursor.getInt(0),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            ArrayList<reservation> notav=new ArrayList<>();
            for(reservation res:r){
                String time_r=res.getTime(dateb);
                if((time_r.compareTo(tor)==0)){
                    notav.add(res);
                }
            }
            return notav;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static table getTD(Context c, int tid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchTD(tid);
            table t=new table(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
            cursor.close();
            dbm.close();
            return t;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<reservation> getReservationsT(Context c, int tid,String date){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchTR(tid,date);
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
    public static ArrayList<Integer> getNeighbouringTables(Context c, int tid,String date){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchNeighT(tid);
            ArrayList<Integer> r = new ArrayList<>(),rl=new ArrayList<>();
            ArrayList<reservation> rlist=new ArrayList<>();
            int wid;

            if (cursor.moveToFirst()) {
                do {
                    r.add(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
            for(int l:r){
                rlist=table.getReservationsT(c,l,date);
                if(!rlist.isEmpty()) {
                    for (reservation r1 : rlist) {
                        wid = reservation.getWaiterId(c, r1.getReservation_id());
                        if(wid==0){
                            rl.add(l);
                        }
                    }
                }

            }

            cursor.close();
            dbm.close();
            return rl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
/*
    public void saveToTable(reservation new_r){
        reservations.add(new_r);
    }

    public void updateTable(int res_id, int waiter_id){
        for(int i=0;i<reservations.size();i++){
            int j= (reservations.get(i)).getReservation_id();
            if(res_id==j){
                reservations.get(i).setWaiterToRes(waiter_id);
            }
        }
    }*/
}
