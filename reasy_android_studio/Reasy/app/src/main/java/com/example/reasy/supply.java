package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class supply {

    public supply(int supply_id, int shop_id, int supplier_id, String address, String sample, double cost) {
        this.supply_id = supply_id;
        this.shop_id = shop_id;
        this.supplier_id = supplier_id;
        this.address = address;
        this.sample = sample;
        this.cost = cost;
    }

    private int supply_id;
    private int shop_id;
    private int supplier_id;
    private String address;
    private String sample;
    private double cost;
    public static ArrayList<supply> getSupplyHistory(Context c, int id){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchSupH(id);
            ArrayList<supply> sl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                  sl.add(new supply(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return sl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public static int getSupplyID(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchSupplyID();
            int o= cursor.getInt(0);
            cursor.close();
            dbm.open();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createSupply(Context c, int shid, int supid, String address, String sample, double cost, ArrayList<product_supplier> arrayList, ArrayList<Integer> sq,ArrayList<Integer> spr){
        try{
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.insertSupply(shid, supid, address, sample, cost);
            int o = supply.getSupplyID(c);
            dbm.close();
            product_supply.createSupplyPr(c,arrayList,o,sq,spr);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
