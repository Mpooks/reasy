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
        Cursor cursor = db.rawQuery("SELECT id,name,cost,quantity FROM m_product WHERE s_id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchNumRes(int id){
        Cursor cursor = db.rawQuery("SELECT num_of_reservations FROM customer WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchA(int sid){
        Cursor cursor = db.rawQuery("SELECT city FROM shop WHERE id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchMPrD(int id){
        Cursor cursor = db.rawQuery("SELECT id,name,cost,quantity FROM m_product WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchOH(int sid){
        Cursor cursor = db.rawQuery("SELECT w_time FROM wo WHERE id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchTables(int sid){
        Cursor cursor = db.rawQuery("SELECT t_id,capacity FROM s_table WHERE s_id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchRes(int tid){
        Cursor cursor = db.rawQuery("SELECT * FROM reservation WHERE t_id="+tid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchCustRes(int cid){
        Cursor cursor = db.rawQuery("SELECT * FROM reservation WHERE c_id="+cid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchOrder(int cid,int sid){
        Cursor cursor = db.rawQuery("SELECT * FROM c_order WHERE c_id="+cid+" AND s_id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchOrderD(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM c_order WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
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
    public void insertOrder(int s_id, int c_id, double cost, String om, String pm){
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("c_id", c_id);
        contentValues.put("cost", cost);
        contentValues.put("om", om);
        contentValues.put("pm", pm);
        db.insert("c_order", null, contentValues);
    }
    public int updateCRes(int cid, int num){
        ContentValues contentValues=new ContentValues();
        contentValues.put("num_of_reservations", num);
        int ret=db.update("customer",contentValues,"id="+cid,null);
        return ret;
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
