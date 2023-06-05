package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class product_supplier extends product{

    private int supplier_id;
    private int available_quantity;

    public product_supplier(int id, String name, double cost, int supplier_id, int available_quantity) {
        super(id,name,cost);
        this.supplier_id = supplier_id;
        this.available_quantity = available_quantity;
    }


    public int getQuantity(){
        return available_quantity;
    }
    public int updateQuantity(int q){
        available_quantity=available_quantity-q;
        return available_quantity;
    }
    public static ArrayList<product_supplier> getSupplierPr(Context c, int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchSP(sid);
            ArrayList<product_supplier> pm = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    pm.add(new product_supplier(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getInt(3),cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbm.close();
            return pm;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static product_supplier getPrD(Context c, int id,int sid){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchPrSD(id,sid);
            product_supplier pm=new product_supplier(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getInt(3),cursor.getInt(4));

            cursor.close();
            dbm.close();
            return pm;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateQuantity(int q, ArrayList<Integer> spr, int pid, ArrayList<product_supplier> ps, int sid, ArrayList<Integer> sq){
        int available_quantity;
        spr.add(pid);
        for (product_supplier p : ps) {
            if (p.getId() == pid) {
                available_quantity=p.getQuantity();
                available_quantity=available_quantity-q;
                ps.set(ps.indexOf(p), new product_supplier(pid, p.getName(), p.getPrice(), sid,available_quantity));
                sq.add(available_quantity);
            }
        }
    }
    public static void updatePSQ(Context c, ArrayList<product_supplier> arrayList, ArrayList<Integer> suq, ArrayList<Integer> supr) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            for (product_supplier r : arrayList) {
                dbm.updatePSupQ(r.getId(), suq.get(supr.indexOf(r.getId())));
            }
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
