package com.example.reasy;

import android.content.Context;

import java.sql.SQLDataException;

public class notification {
    private int customer_id;
    private String description;
    private String date;

    public notification(int customer_id, String description, String date) {
        this.customer_id = customer_id;
        this.description = description;
        this.date = date;
    }
    public static void createNot(Context c, int sid,int jid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            dbm.insertNotif(sid,jid);
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
