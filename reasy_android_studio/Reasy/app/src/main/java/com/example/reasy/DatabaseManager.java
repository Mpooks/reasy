package com.example.reasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DBHandler dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx){
        context=ctx;
    }
    public DatabaseManager open() throws SQLDataException {
        dbHelper=new DBHandler(context);
        db= dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(int id, String email, String password, String name, double balance) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.u_id, id);
        values.put(DBHandler.u_email, email);
        values.put(DBHandler.u_password, password);
        values.put(DBHandler.u_name, name);
        values.put(DBHandler.u_balance, balance);

        db.insert(DBHandler.u_TABLE_NAME, null, values);
    }
    public Cursor fetchU(){
        String [] columns = new String[] {DBHandler.u_id,DBHandler.u_email,DBHandler.u_password, DBHandler.u_name,DBHandler.u_balance};
        Cursor cursor = db.query(DBHandler.u_TABLE_NAME,columns,null,null,null,null,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchS(){
        String [] columns = new String[] {"id","email","password","name", "balance","address","city","capacity","numofrates","cuisine_type","income","expenses","goal","rating","phone"};
        Cursor cursor = db.query("shop",columns,null,null,null,null,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchC(){
        String [] columns = new String[] {"id","email","password","name", "balance","points","num_of_reservations"};
        Cursor cursor = db.query("customer",columns,null,null,null,null,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchPrM(int sid){
        Cursor c = db.rawQuery("SELECT id,name,cost,quantity FROM m_product WHERE s_id="+sid, null);
        if(c !=null){
            c.moveToFirst();
        }
        return c;
    }
    public Cursor fetchOH(int sid){
        Cursor c = db.rawQuery("SELECT w_time FROM wo WHERE id="+sid, null);
        if(c !=null){
            c.moveToFirst();
        }
        return c;
    }
    public Cursor fetchTables(int sid){
        Cursor c = db.rawQuery("SELECT t_id,capacity FROM s_table WHERE s_id="+sid, null);
        if(c !=null){
            c.moveToFirst();
        }
        return c;
    }
    public Cursor fetchRes(int tid){
        Cursor c = db.rawQuery("SELECT * FROM reservation WHERE t_id="+tid, null);
        if(c !=null){
            c.moveToFirst();
        }
        return c;
    }
    public void insertRes(int s_id, int c_id, int numofc, String dateR, String time, int t_id, String address, String requests){
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("c_id", c_id);
        contentValues.put("numofc", numofc);
        contentValues.put("dateR", dateR);
        contentValues.put("time", time);
        contentValues.put("t_id", t_id);
        contentValues.put("address", address);
        contentValues.put("requests", requests);
        db.insert("reservation", null, contentValues);
    }
    public void updateCRes(int cid){
        String up = "UPDATE customer SET num_of_reservations = num_of_reservations+1 WHERE id = "+ cid;
        db.execSQL(up);
    }

    public int update(int id, String email, String password, String name, double balance){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHandler.u_email, email);
        contentValues.put(DBHandler.u_password, password);
        contentValues.put(DBHandler.u_name, name);
        contentValues.put(DBHandler.u_balance, balance);
        int ret=db.update(DBHandler.u_TABLE_NAME,contentValues,DBHandler.u_id+"="+id,null);
        return ret;
    }
    public void delete(int id){
        db.delete(DBHandler.u_TABLE_NAME,DBHandler.u_id+"="+id,null);
    }
}
