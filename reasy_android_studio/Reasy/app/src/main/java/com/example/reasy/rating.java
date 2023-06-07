package com.example.reasy;

import android.content.Context;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class rating {

    private int shop_id;

    private int customer_id;

    private double evaluation;

    public rating(int shop_id, int customer_id, double evaluation) {
        this.shop_id = shop_id;
        this.customer_id = customer_id;
        this.evaluation = evaluation;
    }

    public static void createRating(Context c, int s_id, int c_id, double ev, double sev, int oid, int ok) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.insertSR(s_id,c_id,sev);
            if(ok==1) {
                dbm.insertOR(oid, c_id, ev);
            }
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

}
