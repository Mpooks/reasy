package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class order {
    private int customer_id;
    private int order_id;
    private int shop_id;
    private double cost;
    private String order_method;
    private String payment_method;

    public order(int customer_id, int order_id, int shop_id, double cost, String order_method, String payment_method) {
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.shop_id = shop_id;
        this.cost = cost;
        this.order_method = order_method;
        this.payment_method = payment_method;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public double getCost() {
        return cost;
    }
    public static ArrayList<order> getOrder(Context c, int cid, int sid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchOrder(cid,sid);
            ArrayList<order> o = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    o.add(new order(cursor.getInt(2),cursor.getInt(0),cursor.getInt(1),cursor.getDouble(3),cursor.getString(4),cursor.getString(5)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
