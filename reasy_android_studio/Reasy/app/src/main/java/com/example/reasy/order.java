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
    private int res_id;

    public order(int customer_id, int order_id, int shop_id, double cost, String order_method, String payment_method,int res_id) {
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.shop_id = shop_id;
        this.cost = cost;
        this.order_method = order_method;
        this.payment_method = payment_method;
        this.res_id=res_id;
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
                    o.add(new order(cursor.getInt(2),cursor.getInt(0),cursor.getInt(1),cursor.getDouble(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getOrderID(Context c, int id){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchOrderID(id);
            int o= cursor.getInt(0);
            cursor.close();
            dbm.open();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<order> getOrderD(Context c, int id){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
                Cursor cursor=dbm.fetchOrderD(id);
            ArrayList<order> o = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    o.add(new order(cursor.getInt(2),cursor.getInt(0),cursor.getInt(1),cursor.getDouble(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRes_id() {
        return res_id;
    }

    public String getOrder_method() {
        return order_method;
    }
    public String getPayment_method() {
        return payment_method;
    }
    public static void createO(Context c, String pm, String om, double tc, int sid, int id, int resid, ArrayList<product_menu> arrayList, ArrayList<Integer> oq, ArrayList<Integer> mq, ArrayList<Integer> mpr) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.insertOrder(sid, id, tc, om, pm, resid);
            int o = order.getOrderID(c, resid);
            dbm.close();
            product_order.createPO(c,arrayList,o,oq,mpr);
            product_menu.createPM(c,arrayList,mq,mpr);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}



