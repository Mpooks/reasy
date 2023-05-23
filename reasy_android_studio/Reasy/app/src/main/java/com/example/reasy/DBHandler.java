package com.example.reasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

     static final String DB_NAME = "reasy_db";

    //database version
     static final int DB_VERSION = 1;

     static final String u_TABLE_NAME = "user";
     static final String u_id = "id";
     static final String u_email = "email";
     static final String u_password = "paswword";
     static final String u_name = "name";
     static final String u_balance = "balance";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + u_TABLE_NAME + " ("
                + u_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + u_email + " VARCHAR(255) NOT NULL UNIQUE,"
                + u_password + " VARCHAR(25) NOT NULL,"
                + u_name + " VARCHAR(25) NOT NULL,"
                + u_balance + " DOUBLE NOT NULL)";
       String query1 = "INSERT INTO " + u_TABLE_NAME + " VALUES(2,\"mats@gmail.com\",\"123\",\"Matsuhisa Athens\",1287),(1,\"sal@gmail.com\",\"123\",\"Salumeria\",721.56),(4,\"meze@gmail.com\",\"123\",\"MEZE MEZE\",867),(3,\"pen@gmail.com\",\"123\",\"Peñarrubia Lounge\",987.55),(5,\"josh@gmail.com\",\"123\",\"Josh Payne\",450),(6,\"alex@gmail.com\",\"123\",\"Alex Meyers\",5000),(7,\"kurtis@gmail.com\",\"123\",\"Kurtis Conner\",50),(8,\"danny@gmail.com\",\"123\",\"Danny Gonzalez\",128)";
        db.execSQL(query);
        db.execSQL(query1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + u_TABLE_NAME);
        onCreate(db);
    }
}