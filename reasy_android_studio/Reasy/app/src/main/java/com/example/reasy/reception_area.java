package com.example.reasy;

import android.content.Context;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class reception_area {

    private String name;
    private int reception_area_id;
    private  int num_of_guests;
    private double cost;

    public reception_area(String name, int reception_area_id, int num_of_guests, double cost) {
        this.name = name;
        this.reception_area_id = reception_area_id;
        this.num_of_guests = num_of_guests;
        this.cost = cost;
    }

    public static ArrayList<reception_area> getAvailability(Context c, int n, String d) {
        int av=0;
        ArrayList<reception_area> ra=new ArrayList<>(),fr=new ArrayList<>();
        DatabaseManager dbm = new DatabaseManager(c);
        try {
            dbm.open();
            Cursor cursor1=dbm.fetchRA();
            if (cursor1.moveToFirst()) {
                do {
                    ra.add(new reception_area(cursor1.getString(1),cursor1.getInt(0),cursor1.getInt(3),cursor1.getDouble(2)));
                } while (cursor1.moveToNext());
            }
            cursor1.close();
            for(reception_area r: ra) {
                if(r.getNum_of_guests()>=n){
                        Cursor cursor=dbm.fetchRec(r.getReception_area_id(),d);
                        ArrayList<reception> cl = new ArrayList<>();

                        if (cursor.moveToFirst()) {
                            do {
                                cl.add(new reception(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6)));
                            } while (cursor.moveToNext());
                        }

                        cursor.close();
                        if(cl.isEmpty()){
                            av=1;
                        }
                        else{
                            av=0;
                        }
                }
                else{
                    av=0;
                }
                if(av==1) {
                    fr.add(r);
                }
            }
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        return fr;
    }

    public String getName() {
        return name;
    }

    public int getNum_of_guests() {
        return num_of_guests;
    }

    public int getReception_area_id() {
        return reception_area_id;
    }

    public static double getCost(Context c, int o) {
        double cost;
        try {
        DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor = dbm.fetchRAfromID(o);
            cost=cursor.getDouble(0);
            cursor.close();
            dbm.close();
        }
        catch (SQLDataException e) {
        throw new RuntimeException(e);
        }
        return cost;
    }
}
