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
        String q2= "CREATE TABLE shop(id INTEGER NOT NULL PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE,password VARCHAR(25) NOT NULL, name VARCHAR(25) NOT NULL, balance DOUBLE NOT NULL, address VARCHAR(255) NOT NULL, city VARCHAR(255) NOT NULL, capacity INT NOT NULL, numofrates INT NOT NULL, cuisine_type VARCHAR(25) NOT NULL,income DOUBLE NOT NULL, expenses DOUBLE NOT NULL, goal DOUBLE NOT NULL,rating DOUBLE NOT NULL,phone VARCHAR(25) NOT NULL, FOREIGN KEY(id) REFERENCES user(id),FOREIGN KEY(email) REFERENCES user(email),FOREIGN KEY(password) REFERENCES user(password),FOREIGN KEY(name) REFERENCES user(name),FOREIGN KEY(balance) REFERENCES user(balance))";
        String q3= "CREATE TABLE artist(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL,cost DOUBLE NOT NULL, music_genre VARCHAR(25) NOT NULL)";
        String q4= "CREATE TABLE waiter(w_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL,s_id INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q5= "CREATE TABLE reception_area(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL,cost DOUBLE NOT NULL,numofguests INT NOT NULL)";
        String q6= "CREATE TABLE catering(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL,cost DOUBLE NOT NULL, music_genre VARCHAR(25) NOT NULL)";
        String q7= "CREATE TABLE supplier(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL)";
        String q8= "CREATE TABLE s_table(t_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, capacity INT NOT NULL,s_id INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q9= "CREATE TABLE job_offer(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, s_id INT NOT NULL,position VARCHAR(255) NOT NULL, salary DOUBLE NOT NULL, experience DOUBLE, start_date VARCHAR(255) NOT NULL, end_date VARCHAR(255) NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q10= "CREATE TABLE menu(s_id INT NOT NULL PRIMARY KEY,rating DOUBLE NOT NULL, numofrates INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id))";
        String q11= "CREATE TABLE supply(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, s_id INT NOT NULL,supplier_id INT NOT NULL, address VARCHAR(255) NOT NULL, sample VARCHAR(25) NOT NULL, cost DOUBLE NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id), FOREIGN KEY(supplier_id) REFERENCES supplier(id))";
        String q12= "CREATE TABLE c_order(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, s_id INT NOT NULL,c_id INT NOT NULL, cost DOUBLE NOT NULL, om VARCHAR(25) NOT NULL, pm VARCHAR(25) NOT NULL,res_id INT NOT NULL, FOREIGN KEY(s_id) REFERENCES shop(id), FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(res_id) REFERENCES reservation(id))";
        String q13= "CREATE TABLE product(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL)";
        String q14= "CREATE TABLE m_product(id INTEGER NOT NULL, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,s_id INT NOT NULL, quantity INT NOT NULL, PRIMARY KEY(id,s_id), FOREIGN KEY(s_id) REFERENCES menu(s_id))";
        String q15= "CREATE TABLE supplier_product(id INTEGER NOT NULL, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,s_id INT NOT NULL, quantity INT NOT NULL,PRIMARY KEY(id,s_id), FOREIGN KEY(s_id) REFERENCES supplier(id))";
        String q16= "CREATE TABLE o_product(id INTEGER NOT NULL, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,o_id INT NOT NULL, quantity INT NOT NULL,PRIMARY KEY(id,o_id), FOREIGN KEY(o_id) REFERENCES c_order(id))";
        String q17= "CREATE TABLE reservation(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, s_id INT NOT NULL,c_id INT NOT NULL,numofc INT NOT NULL, dateR VARCHAR(255) NOT NULL, time VARCHAR(255) NOT NULL, t_id INT NOT NULL, address VARCHAR(255), requests VARCHAR(255), FOREIGN KEY(s_id) REFERENCES shop(id),FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(t_id) REFERENCES s_table(t_id))";
        String q18= "CREATE TABLE reception(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, c_id INT NOT NULL,guests INT NOT NULL, dateR VARCHAR(255) NOT NULL, a_id INT NOT NULL, rad INT NOT NULL, cid INT NOT NULL, FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(a_id) REFERENCES artist(id),FOREIGN KEY(rad) REFERENCES reception_area(id),FOREIGN KEY(cid) REFERENCES catering(id))";
        String q19= "CREATE TABLE calendar(c_id INTEGER NOT NULL, r_id INT NOT NULL, dateC VARCHAR(255) NOT NULL, PRIMARY KEY(c_id,r_id), FOREIGN KEY(c_id) REFERENCES customer(id),FOREIGN KEY(r_id) REFERENCES reception(id))";
        String q20= "CREATE TABLE n_t(t_id INTEGER NOT NULL, nt_id INTEGER NOT NULL, PRIMARY KEY(t_id,nt_id), FOREIGN KEY(t_id) REFERENCES s_table(t_id),FOREIGN KEY(nt_id) REFERENCES s_table(t_id))";
        String q21= "CREATE TABLE res_waiter(r_id INTEGER NOT NULL, w_id INT NOT NULL, PRIMARY KEY(r_id,w_id), FOREIGN KEY(r_id) REFERENCES reservation(id),FOREIGN KEY(w_id) REFERENCES waiter(w_id))";
        String q22= "CREATE TABLE wo(id INTEGER NOT NULL, s_day INTEGER NOT NULL, w_time VARCHAR(25) NOT NULL, PRIMARY KEY(id,s_day), FOREIGN KEY(id) REFERENCES shop(id))";
        String q23= "CREATE TABLE rating(s_id INT NOT NULL, c_id NOT NULL, evaluation DOUBLE NOT NULL, PRIMARY KEY(s_id,c_id), FOREIGN KEY(s_id) REFERENCES shop(id), FOREIGN KEY(c_id) REFERENCES customer(id))";
        String q24= "CREATE TABLE supply_product(id INTEGER NOT NULL, name VARCHAR(255) NOT NULL, cost DOUBLE NOT NULL,s_id INT NOT NULL, quantity INT NOT NULL, PRIMARY KEY(id,s_id), FOREIGN KEY(s_id) REFERENCES supply(id))";
        String q25= "CREATE TABLE friend(id INTEGER NOT NULL, f_id INT NOT NULL, PRIMARY KEY(id,f_id), FOREIGN KEY(id) REFERENCES customer(id), FOREIGN KEY(f_id) REFERENCES customer(id))";
        String q26= "CREATE TABLE notif(sid INT NOT NULL, cid INT NOT NULL, jid INT NOT NULL, PRIMARY KEY(sid,cid,jid),  FOREIGN KEY(sid) REFERENCES shop(id), FOREIGN KEY(cid) REFERENCES customer(id), FOREIGN KEY(jid) REFERENCES job_offer(id))";

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
        db.execSQL(q17);
        db.execSQL(q12);
        db.execSQL(q13);
        db.execSQL(q14);
        db.execSQL(q15);
        db.execSQL(q16);
        db.execSQL(q18);
        db.execSQL(q19);
        db.execSQL(q20);
        db.execSQL(q21);
        db.execSQL(q22);
        db.execSQL(q23);
        db.execSQL(q24);
        db.execSQL(q25);
        db.execSQL(q26);

        String query1 = "INSERT INTO " + u_TABLE_NAME + " VALUES(2,\"mats@gmail.com\",\"123\",\"Matsuhisa Athens\",1287),(1,\"sal@gmail.com\",\"123\",\"Salumeria\",721.56),(4,\"meze@gmail.com\",\"123\",\"MEZE MEZE\",867),(3,\"pen@gmail.com\",\"123\",\"Penarrubia Lounge\",987.55),(5,\"josh@gmail.com\",\"123\",\"Josh Payne\",450),(6,\"alex@gmail.com\",\"123\",\"Alex Meyers\",5000),(7,\"kurtis@gmail.com\",\"123\",\"Kurtis Conner\",50),(8,\"danny@gmail.com\",\"123\",\"Danny Gonzalez\",128)";
        String i1 = "INSERT INTO customer VALUES(5,\"josh@gmail.com\",\"123\",\"Josh Payne\",450,25,3),(6,\"alex@gmail.com\",\"123\",\"Alex Meyers\",5000,12,1),(7,\"kurtis@gmail.com\",\"123\",\"Kurtis Conner\",50,134,1),(8,\"danny@gmail.com\",\"123\",\"Danny Gonzalez\",128,0,0)";
        String i2= "INSERT INTO shop VALUES(2,\"mats@gmail.com\",\"123\",\"Matsuhisa Athens\",1287,\"40, Apollonos street, Vouliagmeni 166 71\",\"Athens\",6,820,\"Asian\",7000,4500,2200,4.3,\"2108960510\"),(1,\"sal@gmail.com\",\"123\",\"Salumeria\",721.56,\"Pantanassis 27\",\"Patras\",2,1700,\"Grill\",6000,3500,2000,4.6,\"2610225930\"),(4,\"meze@gmail.com\",\"123\",\"MEZE MEZE\",867,\"Kudonion, Aigaleo\",\"Athens\",3,851,\"Grill\",5500,2500,3000,4.5,\"2105908829\"),(3,\"pen@gmail.com\",\"123\",\"Penarrubia Lounge\",987.55,\"Leoforos Poseidonos 20\",\"Athens\",8,12000,\"Bar\",4500,2000,3000,4.1,\"2109850118\")";
        String i3= "INSERT INTO artist VALUES(1,\"Taylor Swift\",800,\"pop\"),(2,\"Ed Sheeran\",900,\"pop\")";
        String i4= "INSERT INTO waiter VALUES(1,\"John Green\",2),(2,\"Jennifer Lawn\",1),(3,\"Anastasia Pond\",2),(4,\"Drake Lorden\",3),(5,\"Paul Mann\",4)";
        String i5= "INSERT INTO reception_area VALUES(1,\"Haven\",2000,500),(2,\"Pantheon\",1500,200)";
        String i6= "INSERT INTO catering VALUES(1,\"CanRec\",500,\"Asian\"),(2,\"Eataly\",450,\"Italian\")";
        String i7= "INSERT INTO supplier VALUES(1,\"Jim\"),(2,\"Mike\"),(3,\"Phil\")";
        String i8= "INSERT INTO s_table VALUES(1,3,2),(2,2,1),(3,2,1),(4,4,2),(5,2,2),(6,3,4),(7,8,3);";
        String i9= "INSERT INTO job_offer VALUES(1,2,\"waiter\",800,2.5,\"2023-05-20\",\"2023-06-06\")";
        String i10= "INSERT INTO menu VALUES(1,4.5,34),(2,4.8,340),(3,4.5,128),(4,4.2,560)";
        String i11= "INSERT INTO supply VALUES(1,3,1,\"Leoforos Poseidonos 20 Athens\",\"true\",20.5)";
        String i12= "INSERT INTO c_order VALUES(1,2,5,250,\"Online\",\"Online\",2)";
        String i13= "INSERT INTO product VALUES(1,\"First Time Omakase\", 100),(2,\"Special Omakase\", 250),(3,\"Crispy Rice Spicy Salmon\", 35),(4,\"Wagyu Tacos\", 50),(5,\"Benedict\", 8),(6,\"Mmontreal\", 8.5),(7,\"Meze Meze Salad\", 7.8),(8,\"Cheese plateau\", 16),(9,\"Tomatoes\", 0.5125),(10,\"Potatoes\", 0.34),(11,\"Cucumbers\", 0.65),(12,\"Eggplants\", 0.89),(13,\"Carrots\", 0.42),(14,\"Lettuce\", 0.75)";
        String i14= "INSERT INTO m_product VALUES(1,\"First Time Omakase\", 100,2,23),(2,\"Special Omakase\", 250,2,2),(3,\"Crispy Rice Spicy Salmon\", 35,2,18),(4,\"Wagyu Tacos\", 50,2,7),(5,\"Benedict\", 8,3,16),(6,\"Mmontreal\", 8.5,3,9),(7,\"Meze Meze Salad\", 7.8,4,23),(8,\"Cheese plateau\", 16,1,7)";
        String i15= "INSERT INTO supplier_product VALUES(9,\"Tomatoes\", 0.5125,1,80),(10,\"Potatoes\", 0.34,1,34),(11,\"Cucumbers\", 0.65,2,23),(12,\"Eggplants\", 0.89,2,34),(13,\"Carrots\", 0.42,3,69),(14,\"Lettuce\", 0.75,3,54)";
        String i16= "INSERT INTO o_product VALUES(1,\"First Time Omakase\",100,1,2),(4,\"Wagyu Tacos\", 50,1,1)";
        String i17= "INSERT INTO reservation VALUES(1,1,5,2,\"2023-05-20\",\"20:30\",2,null,null),(2,2,5,4,\"2023-05-19\",\"21:30\",4,null,null),(3,1,5,2,\"2023-05-01\",\"20:30\",3,null,null),(4,3,6,2,\"2023-05-21\",\"22:30\",1,null,null),(5,4,7,3,\"2023-05-01\",\"19:30\",6,null,null)";
        String i18= "INSERT INTO reception VALUES(1,5,125,\"2023-05-25\",1,1,1)";
        String i19= "INSERT INTO calendar VALUES(6,1,\"2023-05-25\"),(7,1,\"2023-05-25\")";
        String i20= "INSERT INTO n_t VALUES(1,6),(6,1),(4,5),(5,4)";
        String i21= "INSERT INTO res_waiter VALUES(1,2),(2,1),(3,3),(4,4),(5,5)";
        String i22= "INSERT INTO wo VALUES(1,0,\"8:00-18:00\"),(1,1,\"8:00-21:00\"),(1,2,\"8:00-21:00\"),(1,3,\"8:00-21:00\"),(1,4,\"8:00-21:00\"),(1,5,\"8:00-21:00\"),(1,6,\"closed\"),(2,0,\"8:00-18:00\"),(2,1,\"8:00-21:00\"),(2,2,\"8:00-21:00\"),(2,3,\"8:00-21:00\"),(2,4,\"8:00-21:00\"),(2,5,\"8:00-21:00\"),(2,6,\"closed\"),(3,0,\"8:00-18:00\"),(3,1,\"8:00-21:00\"),(3,2,\"8:00-21:00\"),(3,3,\"8:00-21:00\"),(3,4,\"8:00-21:00\"),(3,5,\"8:00-21:00\"),(3,6,\"closed\"),(4,0,\"8:00-18:00\"),(4,1,\"8:00-21:00\"),(4,2,\"8:00-21:00\"),(4,3,\"8:00-21:00\"),(4,4,\"8:00-21:00\"),(4,5,\"8:00-21:00\"),(4,6,\"closed\")";
        String i23= "INSERT INTO rating VALUES(2,1,4.3)";
        String i24= "INSERT INTO supply_product VALUES(9,\"Tomatoes\",0.5125,1,40)";
        String i25= "INSERT INTO friend VALUES(1,2),(1,3),(2,1),(2,4),(3,1),(4,2)";

        db.execSQL(query1);
        db.execSQL(i1);
        db.execSQL(i2);
        db.execSQL(i3);
        db.execSQL(i4);
        db.execSQL(i5);
        db.execSQL(i6);
        db.execSQL(i7);
        db.execSQL(i8);
        db.execSQL(i9);
        db.execSQL(i10);
        db.execSQL(i11);
        db.execSQL(i17);
        db.execSQL(i12);
        db.execSQL(i13);
        db.execSQL(i14);
        db.execSQL(i15);
        db.execSQL(i16);
        db.execSQL(i18);
        db.execSQL(i19);
        db.execSQL(i20);
        db.execSQL(i21);
        db.execSQL(i22);
        db.execSQL(i23);
        db.execSQL(i24);
        db.execSQL(i25);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + u_TABLE_NAME);
        onCreate(db);
    }
}