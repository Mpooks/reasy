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
        String q1= "CREATE TABLE customer(id INTEGER NOT NULL PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE,password VARCHAR(25) NOT NULL, name VARCHAR(25) NOT NULL, balance DOUBLE NOT NULL, points INT NOT NULL, num_of_reservations INT NOT NULL, FOREIGN KEY(id) REFERENCES user(id),FOREIGN KEY(email) REFERENCES user(email),FOREIGN KEY(password) REFERENCES user(password),FOREIGN KEY(name) REFERENCES user(name),FOREIGN KEY(balance) REFERENCES user(balance))";
        String q2= "CREATE TABLE shop(id INTEGER NOT NULL PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE,password VARCHAR(25) NOT NULL, name VARCHAR(25) NOT NULL, balance DOUBLE NOT NULL, address VARCHAR(255) NOT NULL, city VARCHAR(255) NOT NULL, capacity INT NOT NULL, numofrates INT NOT NULL, cuisine_type VARCHAR(25) CHECK( pType IN ('Asian','Italian','Grill') ),income DOUBLE NOT NULL, expenses DOUBLE NOT NULL, goal DOUBLE NOT NULL,rating DOUBLE NOT NULL,phone VARCHAR(25) NOT NULL, FOREIGN KEY(id) REFERENCES user(id),FOREIGN KEY(email) REFERENCES user(email),FOREIGN KEY(password) REFERENCES user(password),FOREIGN KEY(name) REFERENCES user(name),FOREIGN KEY(balance) REFERENCES user(balance))";
        String q3= "CREATE TABLE artist(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL,cost DOUBLE NOT NULL, music_genre VARCHAR(25) CHECK( pType IN ('Pop','Rock','Indie') ) NOT NULL)";
        String q4= "CREATE TABLE waiter(w_id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL,s_id INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q5= "CREATE TABLE reception_area(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL,cost DOUBLE NOT NULL,numofguests INT NOT NULL)";
        String q6= "CREATE TABLE catering(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL,cost DOUBLE NOT NULL, cuisine_type VARCHAR(25) CHECK( pType IN ('Asian','Italian','Grill') ) NOT NULL)";
        String q7= "CREATE TABLE supplier(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL)";
        String q8= "CREATE TABLE s_table(t_id INTEGER NOT NULL PRIMARY KEY, s_id INT NOT NULL,capacity INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q9= "CREATE TABLE job_offer(id INTEGER NOT NULL PRIMARY KEY, s_id INT NOT NULL,position VARCHAR(255) NOT NULL, salary DOUBLE NOT NULL, experience DOUBLE, start_date VARCHAR(255) NOT NULL, end_date VARCHAR(255) NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q10= "CREATE TABLE menu(id INTEGER NOT NULL PRIMARY KEY, s_id INT NOT NULL,RATING DOUBLE NOT NULL, numofrates INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q11= "CREATE TABLE supply(id INTEGER NOT NULL PRIMARY KEY, s_id INT NOT NULL,supplier_id INT NOT NULL, address VARCHAR(255) NOT NULL, sample VARCHAR(25) CHECK( pType IN ('true','false') ) NOT NULL, cost DOUBLE NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id), FOREIGN KEY(supplier_id) REFERENCES supplier(id))";
        String q12= "CREATE TABLE c_order(id INTEGER NOT NULL PRIMARY KEY, s_id INT NOT NULL,c_id INT NOT NULL, cost DOUBLE NOT NULL, om CHECK( pType IN ('Online','In person') ) NOT NULL, po CHECK( pType IN ('Online','In person') ) NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id), FOREIGN KEY(c_id) REFERENCES customer(id))";
        String q13= "CREATE TABLE product(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL)";
        String q14= "CREATE TABLE m_product(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,m_id INT NOT NULL, quantity INT NOT NULL, FOREIGN KEY(m_id) REFERENCES menu(id))";
        String q15= "CREATE TABLE supplier_product(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,s_id INT NOT NULL, quantity INT NOT NULL, FOREIGN KEY(s_id) REFERENCES supplier(id))";
        String q16= "CREATE TABLE o_product(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,o_id INT NOT NULL, quantity INT NOT NULL, FOREIGN KEY(o_id) REFERENCES c_order(id))";
        String q17= "CREATE TABLE resrvation(id INTEGER NOT NULL PRIMARY KEY, s_id INT NOT NULL,c_id INT NOT NULL,numofc INT NOT NULL, dateR VARCHAR(255) NOT NULL, time VARCHAR(255) NOT NULL, t_id INT NOT NULL, address VARCHAR(255), requests VARCHAR(255), FOREIGN KEY(s_id) REFERENCES shop(id),FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(t_id) REFERENCES s_table(t_id))";
        String q18= "CREATE TABLE reception(id INTEGER NOT NULL PRIMARY KEY, c_id INT NOT NULL,guests INT NOT NULL, dateR VARCHAR(255) NOT NULL, a_id INT NOT NULL, rad INT NOT NULL, cid INT NOT NULL, FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(a_id) REFERENCES artist(id),FOREIGN KEY(rad) REFERENCES reception_area(id),FOREIGN KEY(cid) REFERENCES catering(id))";
        String q19= "CREATE TABLE calendar(c_id INTEGER NOT NULL PRIMARY KEY, r_id INT NOT NULL PRIMARY KEY, dateC VARCHAR(255) NOT NULL, FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(r_id) REFERENCES reception(id))";
        String q20= "CREATE TABLE n_t(t_id INTEGER NOT NULL PRIMARY KEY, nt_id INTEGER NOT NULL PRIMARY KEY, FOREIGN KEY(t_id) REFERENCES s_table(t_id),FOREIGN KEY(nt_id) REFERENCES s_table(t_id))";
        String q21= "CREATE TABLE res_waiter(r_id INTEGER NOT NULL PRIMARY KEY, w_id INT NOT NULL PRIMARY KEY, FOREIGN KEY(r_id) REFERENCES reservation(id),FOREIGN KEY(w_id) REFERENCES waiter(w_id))";
        String q22= "CREATE TABLE wo(id INTEGER NOT NULL PRIMARY KEY, s_day VARCHAR(25) CHECK( pType IN ('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') ) NOT NULL PRIMARY KEY, w_time VARCHAR(25) NOT NULL, FOREIGN KEY(id) REFERENCES shop(id))";
        db.execSQL(query);
        db.execSQL(q1);
        db.execSQL(q2);
        db.execSQL(q3);
        db.execSQL(q4);
        db.execSQL(q5);
        db.execSQL(q6);
        db.execSQL(q7);
        db.execSQL(q8);
        db.execSQL(q9);
        db.execSQL(q10);
        db.execSQL(q11);
        db.execSQL(q12);
        db.execSQL(q13);
        db.execSQL(q14);
        db.execSQL(q15);
        db.execSQL(q16);
        db.execSQL(q17);
        db.execSQL(q18);
        db.execSQL(q19);
        db.execSQL(q20);
        db.execSQL(q21);
        db.execSQL(q22);

        String query1 = "INSERT INTO " + u_TABLE_NAME + " VALUES(2,\"mats@gmail.com\",\"123\",\"Matsuhisa Athens\",1287),(1,\"sal@gmail.com\",\"123\",\"Salumeria\",721.56),(4,\"meze@gmail.com\",\"123\",\"MEZE MEZE\",867),(3,\"pen@gmail.com\",\"123\",\"Peñarrubia Lounge\",987.55),(5,\"josh@gmail.com\",\"123\",\"Josh Payne\",450),(6,\"alex@gmail.com\",\"123\",\"Alex Meyers\",5000),(7,\"kurtis@gmail.com\",\"123\",\"Kurtis Conner\",50),(8,\"danny@gmail.com\",\"123\",\"Danny Gonzalez\",128)";
        String i1 = "INSERT INTO customer VALUES(5,\"josh@gmail.com\",\"123\",\"Josh Payne\",450,25,3),(6,\"alex@gmail.com\",\"123\",\"Alex Meyers\",5000,12,1),(7,\"kurtis@gmail.com\",\"123\",\"Kurtis Conner\",50,134,1),(8,\"danny@gmail.com\",\"123\",\"Danny Gonzalez\",128,0,0)";
        String i2= "INSERT INTO shop VALUES(2,\"mats@gmail.com\",\"123\",\"Matsuhisa Athens\",1287,\"40, Apollonos street, Vouliagmeni 166 71\",\"Athens\",6,820,\"Asian\",7000,4500,2200,4.3,\"2108960510\"),(1,\"sal@gmail.com\",\"123\",\"Salumeria\",721.56,\"Pantanassis 27\",\"Patras\",2,1700,\"Grill\",6000,3500,2000,4.6,\"2610225930\"),(4,\"meze@gmail.com\",\"123\",\"MEZE MEZE\",867,\"Kudonion, Aigaleo\",\"Athens\",3,851,\"Grill\",5500,2500,3000,4.5,\"2105908829\"),(3,\"pen@gmail.com\",\"123\",\"Peñarrubia Lounge\",987.55,\"Leoforos Poseidonos 20\",\"Athens\",8,12000,\"Bar\",4500,2000,3000,4.1,\"2109850118\")";
        String i3= "INSERT INTO artist VALUES(1,\"Taylor Swift\",800,\"pop\"),(2,\"Ed Sheeran\",900,\"pop\")";
        String i4= "INSERT INTO waiter VALUES(1,\"John Green\",2),(2,\"Jennifer Lawn\",1),(3,\"Anastasia Pond\",2),(4,\"Drake Lorden\",3),(5,\"Paul Mann\",4)";
        String i5= "INSERT INTO reception_area VALUES(1,\"Haven\",2000,500),(2,\"Pantheon\",1500,200)";
        String i6= "INSERT INTO catering VALUES(1,\"CanRec\",500,\"Asian\"),(2,\"Eataly\",450,\"Italian\")";
        String i7= "INSERT INTO supplier VALUES(1,\"Jim\"),(2,\"Mike\"),(3,\"Phil\")";
        String i8= "";


        db.execSQL(query1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + u_TABLE_NAME);
        onCreate(db);
    }
}