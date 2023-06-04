package com.example.reasy;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class invitation {

    private int reception_id;

    private int customer_id;

    private String date;

    public invitation(int reception_id, int customer_id, String date) {
        this.reception_id = reception_id;
        this.customer_id = customer_id;
        this.date = date;
    }
    public static void createInv(Context c, ArrayList<Integer> cid,int rid,String date) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            for(int j:cid) {
                dbm.insertInv(j, rid, date);
            }
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

}
