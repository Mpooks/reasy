package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class catering {

    private String name;
    private int catering_id;
    private String cuisine_type;
    private double cost;

    public catering(int catering_id, String name, double cost, String cuisine_type) {
        this.name = name;
        this.catering_id = catering_id;
        this.cuisine_type = cuisine_type;
        this.cost = cost;
    }

    public int getCatering() {
        return catering_id;
    }

    public String getCuisineType() {
        return cuisine_type;
    }

    public String getName() {
        return name;
    }
    public static ArrayList<catering> getCaterings(Context c){
        DatabaseManager dbm = new DatabaseManager(c);
        ArrayList<catering> cat=new ArrayList<>();

        try {
            dbm.open();
            Cursor cursor=dbm.fetchCat();
            if (cursor.moveToFirst()) {
                do {
                    cat.add(new catering(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getString(3)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        return cat;
    }

}
