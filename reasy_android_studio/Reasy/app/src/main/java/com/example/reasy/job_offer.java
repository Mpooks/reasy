package com.example.reasy;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;
public class job_offer {

    private int shop_id;
    private String position;
    private double salary;
    private double experience;
    private String start_date;
    private String end_date;
    private int id;

    public job_offer(int id, int shop_id, String position, double salary, double experience, String start_date, String end_date) {
        this.id=id;
        this.shop_id = shop_id;
        this.position = position;
        this.salary = salary;
        this.experience = experience;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public static double getMedianSalary(ArrayList<job_offer> jl, String pos){
        ArrayList<job_offer> jo=new ArrayList<>();
        double meds=0;
        int n=0;
        for(job_offer j:jl){
            if(j.getPosition().compareTo(pos)==0){
                jo.add(j);
            }
        }
        for(job_offer j1:jo){
            meds=meds+j1.getSalary();
            n++;
        }
        if(jo.isEmpty()){
            meds=0;
        }
        else {
            meds = meds / n;
        }
        return meds;
    }

    public String getPosition() {
        return position;
    }
    public double getSalary(){
        return salary;
    }

    public static ArrayList<job_offer> getJO(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchJO();
            ArrayList<job_offer> j = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    j.add(new job_offer(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5),cursor.getString(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return j;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

}
