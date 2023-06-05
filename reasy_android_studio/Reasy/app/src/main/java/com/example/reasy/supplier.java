package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class supplier {

    private int supplier_id;
    private String name;


    public supplier(int supplier_id, String name) {
        this.supplier_id = supplier_id;
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public int getId() {
        return supplier_id;
    }

    public static ArrayList<supplier> getSupplierList(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchSL();
            ArrayList<supplier> r = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    r.add(new supplier(cursor.getInt(0),cursor.getString(1)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return r;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<product_supplier> getSupplierProducts(Context c, int sid) {
        ArrayList<product_supplier> ps = new ArrayList<>();
        ps = product_supplier.getSupplierPr(c,sid);
        return ps;
    }
}
