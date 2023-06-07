package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class reception {

    private int guest_number;
    private int reception_id;
    private int customer_id;
    private String date;
    private int artist_id;
    private int reception_area_id;
    private int catering_id;

    public reception(int reception_id, int customer_id,int guest_number, String date, int artist_id, int reception_area_id, int catering_id) {
        this.guest_number = guest_number;
        this.reception_id = reception_id;
        this.customer_id = customer_id;
        this.date = date;
        this.artist_id = artist_id;
        this.reception_area_id = reception_area_id;
        this.catering_id = catering_id;
    }


    public void changeReception(int new_number)   {
        guest_number=new_number;
    }

    public int getReception_id() {
        return reception_id;
    }

    public String getDate() {
        return date;
    }
    public int getInv(Context c, int rid) {
        int av=0;
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchInv(rid);
            ArrayList<invitation> cl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    cl.add(new invitation(cursor.getInt(0),cursor.getInt(1),cursor.getString(2)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            if(cl.isEmpty()){
                av=1;
            }
            else{
                av=0;
            }
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        return av;
    }

    public static void updateRecN(Context c, int rid, int newnum, int fnum){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            if(newnum<fnum) {
                dbm.updateNOI(rid, newnum);
            }
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getGuest_number(Context c,int rid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchRecN(rid);
            int n=cursor.getInt(0);
            dbm.close();
            return n;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createRec(Context c,int id,int g, String date,int car,int ca,int cc){
        DatabaseManager dbm = new DatabaseManager(c);
        try {
            dbm.open();
            dbm.insertR(id,g,date,car,ca,cc);
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

}
