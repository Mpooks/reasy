package com.example.reasy;

import android.content.Context;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class product_supply extends product{

    private int supply_id;
    private int quantity;
    public product_supply(int id, String name, double cost, int supply_id, int quantity) {
        super(id,name,cost);
        this.supply_id = supply_id;
        this.quantity = quantity;
    }

    public static void createPS(int pid, String n, double p, int q, ArrayList<Integer> spr, ArrayList<Integer> sq){
        product_supply ps = new product_supply(pid,n,p,0,q);
        spr.add(pid);
        sq.add(q);
    }
    public static void createSupplyPr(Context c, ArrayList<product_supplier> arrayList, int supid, ArrayList<Integer> sq, ArrayList<Integer> spr) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            for (product_supplier r : arrayList) {
                dbm.insertSupplyPr(r.getId(), r.getName(), r.getPrice(), supid, sq.get(spr.indexOf(r.getId())));
            }
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
