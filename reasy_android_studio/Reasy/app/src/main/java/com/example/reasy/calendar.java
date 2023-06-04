package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class calendar {

    private int customer_id;

    private int reception_id;

    private String date;

    public calendar(int customer_id, int reception_id, String date) {
        this.customer_id = customer_id;
        this.reception_id = reception_id;
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public static int getAv(Context c, int cid,String d) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor = dbm.fetchAv(cid,d);
            ArrayList<calendar> rl = new ArrayList<>();
            int a;

            if (cursor.moveToFirst()) {
                do {
                    rl.add(new calendar(cursor.getInt(0),cursor.getInt(1),cursor.getString(2)));
                } while (cursor.moveToNext());
            }
            if(rl.isEmpty()){
                a=1;
            }
            else{
                a=0;
            }

            cursor.close();
            dbm.close();
            return a;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
