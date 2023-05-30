package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class product_menu extends product{
    public product_menu(int id, String name, double cost, int shop_id, int available_quantity) {
        super(id,name,cost);
        this.shop_id = shop_id;
        this.available_quantity = available_quantity;
    }

    private int shop_id;
    private int available_quantity;

    public int getQuantity(){
        return available_quantity;
    }
    public int updateQuantity(int q){
        available_quantity=available_quantity-q;
        return available_quantity;
    }
    public static ArrayList<product_menu> getMenuPr(Context c, int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchPrM(sid);
            ArrayList<product_menu> pm = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    pm.add(new product_menu(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),sid,cursor.getInt(3)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbm.close();
            return pm;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static product_menu getMPrD(Context c, int id,int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchMPrD(id);
            product_menu pm=new product_menu(id,cursor.getString(1),cursor.getDouble(2),sid,cursor.getInt(3));

            cursor.close();
            dbm.close();
            return pm;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
