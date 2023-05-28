package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class customer extends user{
    private int points;
    private int num_of_reservations;

    public customer(String email, String password,String name, int id, double balance, int points, int num_of_reservations) {
        super(email,password,name, id, balance);
        this.points = points;
        this.num_of_reservations = num_of_reservations;
    }

    public void addPointsRShop(int p){
        points=points+p;
    }
    public void addPointsROrder(int p, order o){
        points=points+p;
    }
    public int getNumOfReservations(){
        return num_of_reservations;
    }
    public void updateNumOfReservations(Context c,int cid){
        num_of_reservations++;
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.updateCRes(cid,num_of_reservations);
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPoints() {
        return points;
    }
    public static ArrayList<customer> getCustomer(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchC();
            ArrayList<customer> cl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    cl.add(new customer(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(0),cursor.getDouble(4),cursor.getInt(5),cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return cl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<reservation> getReservations(Context c,int cid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchCustRes(cid);
            ArrayList<reservation> rl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    rl.add(new reservation(cursor.getInt(1),cursor.getInt(2),cursor.getInt(0),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return rl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<order> getOrderHistory(Context c,int cid,int sid) {
        ArrayList<order> o = new ArrayList<>();
        o = order.getOrder(c,cid,sid);
        return o;
    }
}
