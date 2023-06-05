package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class product_order extends product{

    private int order_id;
    private int quantity;

    public product_order(int id,String name, double cost, int order_id, int quantity) {
        super(id,name,cost);
        this.order_id = order_id;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }
    public static ArrayList<product_order> getOrderD(Context c, int id){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchPrO(id);
            ArrayList<product_order> o = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    o.add(new product_order(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getInt(3),cursor.getInt(4)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createPO(Context c, ArrayList<product_menu> arrayList, int oid, ArrayList<Integer> oq, ArrayList<Integer> mpr) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            for (product_menu r : arrayList) {
                dbm.insertPrO(r.getId(), r.getName(), r.getPrice(), oid, oq.get(mpr.indexOf(r.getId())));
            }
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
