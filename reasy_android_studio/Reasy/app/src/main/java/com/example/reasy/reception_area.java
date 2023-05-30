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

    public int getAvailability(Context c, int n, String d,int rid,int num) {
        int av=0;
        if(num>=n){
            try {
                DatabaseManager dbm = new DatabaseManager(c);
                dbm.open();
                Cursor cursor=dbm.fetchRec(rid,d);
                ArrayList<reception> cl = new ArrayList<>();

                if (cursor.moveToFirst()) {
                    do {
                        cl.add(new reception(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6)));
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
        }
        else{
            av=0;
        }
        return av;
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

    public double getCost() {
        return cost;
    }
}
