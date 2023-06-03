package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class menu {
    private int shop_id;
    private double rating;
    private int num_of_rates;
    public menu(int shop_id, double rating, int num_of_rates) {
        this.shop_id = shop_id;
        this.rating = rating;
        this.num_of_rates=num_of_rates;
    }

    public static ArrayList<product_menu> getMenu(Context c, int sid) {
        ArrayList<product_menu> pm = new ArrayList<>();
        pm = product_menu.getMenuPr(c,sid);
        return pm;
    }
    public static void changeMenuRating(Context c, int sid, double newr){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchMR(sid);
            int n;
            double r,nrat;
            n=cursor.getInt(1);
            r=cursor.getDouble(0);
            double pr_r=n*r;
            n=n+1;
            nrat=(pr_r+newr)/n;
            dbm.updateMR(sid,nrat,n);
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
