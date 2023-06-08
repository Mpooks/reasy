package com.example.reasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import java.util.ArrayList;

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
    public Cursor fetchR(int sid){
        Cursor cursor = db.rawQuery("SELECT rating,numofrates FROM shop WHERE id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchMR(int sid){
        Cursor cursor = db.rawQuery("SELECT rating,numofrates FROM menu WHERE s_id="+sid, null);
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
    public Cursor fetchPrSD(int id,int sid){
        Cursor cursor = db.rawQuery("SELECT * FROM supplier_product WHERE s_id="+sid+" AND id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchSP(int sid){
        Cursor cursor = db.rawQuery("SELECT * FROM supplier_product WHERE s_id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchSupH(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM supply WHERE s_id="+id, null);
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
    public Cursor fetchRH(int cid,int sid){
        Cursor cursor = db.rawQuery("SELECT s_id,c_id,evaluation FROM rating WHERE c_id="+cid+" AND s_id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchRO(int cid,int sid){
        Cursor cursor = db.rawQuery("SELECT oid FROM rated_o WHERE cid="+cid+" AND oid="+sid, null);
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
    public Cursor fetchTables(int sid,int cap){
        Cursor cursor = db.rawQuery("SELECT t_id,capacity FROM s_table WHERE s_id="+sid+" AND capacity>="+cap, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchTD(int tid){
        Cursor cursor = db.rawQuery("SELECT * FROM s_table WHERE t_id="+tid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchWD(int wid){
        Cursor cursor = db.rawQuery("SELECT * FROM waiter WHERE w_id="+wid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchTR(int tid,String date){
        Cursor cursor = db.rawQuery("SELECT * FROM reservation WHERE t_id="+tid+" AND dateR='"+date+"'", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchNeighT(int tid){
        Cursor cursor = db.rawQuery("SELECT nt_id FROM n_t WHERE t_id="+tid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchWI(int rid){
        Cursor cursor = db.rawQuery("SELECT w_id FROM res_waiter WHERE r_id="+rid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchWaiters(int sid){
        Cursor cursor = db.rawQuery("SELECT * FROM waiter WHERE s_id="+sid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchRD(int rid){
        Cursor cursor = db.rawQuery("SELECT * FROM reservation WHERE id="+rid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchTablesInfo(int sid){
        Cursor cursor = db.rawQuery("SELECT * FROM s_table WHERE s_id="+sid, null);
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
    public Cursor fetchCustRec(int cid){
        Cursor cursor = db.rawQuery("SELECT * FROM reception WHERE c_id="+cid, null);
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
        Cursor cursor = db.rawQuery("SELECT * FROM c_order WHERE c_id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchInv(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM invitation WHERE rid="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public ArrayList<Integer> fetchFL(int id,int rid){
        ArrayList<Integer> i=new ArrayList<>(),n=new ArrayList<>(),f=new ArrayList<>(),set=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT f_id FROM friend WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        if (cursor.moveToFirst()) {
            do {
                n.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        Cursor cursor1 = db.rawQuery("SELECT c_id FROM calendar WHERE r_id=" + rid, null);
        if (cursor1 != null) {
            cursor1.moveToFirst();
        }
        if (cursor1.moveToFirst()) {
            do {
                i.add(cursor1.getInt(0));
            } while (cursor1.moveToNext());
        }
        if(!n.isEmpty()) {
            if(i.isEmpty()){
                f.addAll(n);
            }
            else {
                f.addAll(n);
                f.removeAll(i);
                }
            }
        return f;
    }
    public Cursor fetchOrderID(int id){
        Cursor cursor = db.rawQuery("SELECT id FROM c_order WHERE res_id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchSupplyID(){
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM supply", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchRA(){
        Cursor cursor = db.rawQuery("SELECT * FROM reception_area", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchSL(){
        Cursor cursor = db.rawQuery("SELECT * FROM supplier", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchRAfromID(int rid){
        Cursor cursor = db.rawQuery("SELECT cost FROM reception_area WHERE id="+rid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchRec(int rid,String d){
        Cursor cursor = db.rawQuery("SELECT * FROM reception WHERE rad="+rid+" AND dateR='"+d+"'", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchAv(int cid,String d){
        Cursor cursor = db.rawQuery("SELECT * FROM calendar WHERE c_id="+cid+" AND dateC='"+d+"'", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchPrO(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM o_product WHERE o_id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchB(int id){
        Cursor cursor = db.rawQuery("SELECT balance FROM user WHERE id="+id, null);
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
    public void insertOrder(int s_id, int c_id, double cost, String om, String pm,int res_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("c_id", c_id);
        contentValues.put("cost", cost);
        contentValues.put("om", om);
        contentValues.put("pm", pm);
        contentValues.put("res_id", res_id);
        db.insert("c_order", null, contentValues);
    }
    public void insertSupplyPr(int id, String name, double cost, int s_id, int q){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("cost", cost);
        contentValues.put("s_id", s_id);
        contentValues.put("quantity", q);
        db.insert("supply_product", null, contentValues);
    }
    public void insertSupply(int s_id,int supid, String ad, String s,double cost){
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("supplier_id", supid);
        contentValues.put("address", ad);
        contentValues.put("sample", s);
        contentValues.put("cost", cost);
        db.insert("supply", null, contentValues);
    }
    public void insertSR(int s_id, int c_id,double ev){
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("c_id", c_id);
        contentValues.put("evaluation", ev);
        db.insertOrThrow("rating", null, contentValues);
    }
    public void insertInv(int cid, int rid,String date, String r){
        ContentValues contentValues = new ContentValues();
        contentValues.put("rid", rid);
        contentValues.put("cid", cid);
        contentValues.put("dateR", date);
        contentValues.put("descr", r);
        db.insertOrThrow("invitation", null, contentValues);
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("c_id", cid);
        contentValues1.put("r_id", rid);
        contentValues1.put("dateC", date);
        db.insertOrThrow("calendar", null, contentValues1);
    }
    public void insertAss(int r_id, int w_id, ArrayList<Integer> tid, String date,int w){
        if(w==1) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("r_id", r_id);
            contentValues.put("w_id", w_id);
            db.insertOrThrow("res_waiter", null, contentValues);
        }
        for(int i:tid) {
            Cursor cursor = db.rawQuery("SELECT id FROM reservation WHERE t_id=" + i + " AND dateR='" + date + "'", null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            if (cursor.moveToFirst()) {
                do {
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("r_id", cursor.getInt(0));
                    contentValues1.put("w_id", w_id);
                    db.insertOrThrow("res_waiter", null, contentValues1);
                } while (cursor.moveToNext());
            }
        }
    }
    public void insertOR(int oid, int c_id, double ev){
        ContentValues contentValues = new ContentValues();
        contentValues.put("oid", oid);
        contentValues.put("cid", c_id);
        contentValues.put("evaluation", ev);
        db.insert("rated_o", null, contentValues);
    }
    public void insertNotif(int sid, int jid){
        ContentValues contentValues = new ContentValues();
        contentValues.put("sid", sid);
        contentValues.put("jid", jid);
        db.insert("notif", null, contentValues);
    }
    public int insertJO(int sid,String pos,double sal,double exp,String sd,String ed){
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", sid);
        contentValues.put("position", pos);
        contentValues.put("salary", sal);
        contentValues.put("experience", exp);
        contentValues.put("start_date", sd);
        contentValues.put("end_date", ed);
        db.insert("job_offer", null, contentValues);
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM job_offer", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor.getInt(0);
    }
    public void insertR(int id, int g, String date, int car, int ca,int cc){
        ContentValues contentValues = new ContentValues();
        if(ca!=0){
            contentValues.put("a_id", ca);
        }
        if(cc!=0){
            contentValues.put("cid", cc);
        }
        contentValues.put("c_id", id);
        contentValues.put("guests", g);
        contentValues.put("dateR", date);
        contentValues.put("rad", car);
        db.insert("reception", null, contentValues);
    }
    public void insertPrO(int id, String name, double cost, int o_id, int quantity){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("cost", cost);
        contentValues.put("o_id", o_id);
        contentValues.put("quantity", quantity);
        db.insert("o_product", null, contentValues);
    }
    public void updatePrMQ(int id, int q){
        ContentValues contentValues=new ContentValues();
        contentValues.put("quantity", q);
        int ret=db.update("m_product",contentValues,"id="+id,null);
    }
    public void updatePSupQ(int id, int q){
        ContentValues contentValues=new ContentValues();
        contentValues.put("quantity", q);
        int ret=db.update("supplier_product",contentValues,"id="+id,null);
    }
    public void updateB(int id, double q){
        ContentValues contentValues=new ContentValues();
        contentValues.put("balance", q);
        int ret=db.update("user",contentValues,"id="+id,null);
    }
    public void updateP(int id,int p){
        if(p==0) {
            String strSQL = "UPDATE customer SET points = points+50 WHERE id = " + id;
            db.execSQL(strSQL);
        }
        else{
            String strSQL = "UPDATE customer SET points = points+20 WHERE id = " + id;
            db.execSQL(strSQL);
        }
    }
    public int updateCRes(int cid, int num){
        ContentValues contentValues=new ContentValues();
        contentValues.put("num_of_reservations", num);
        int ret=db.update("customer",contentValues,"id="+cid,null);
        return ret;
    }
    public void updateR(int sid,double nrat, int n){
        ContentValues contentValues=new ContentValues();
        contentValues.put("numofrates", n);
        contentValues.put("rating", nrat);
        int ret=db.update("shop",contentValues,"id="+sid,null);
    }
    public void updateMR(int sid,double nrat, int n){
        ContentValues contentValues=new ContentValues();
        contentValues.put("numofrates", n);
        contentValues.put("rating", nrat);
        int ret=db.update("menu",contentValues,"s_id="+sid,null);
    }
    public Cursor fetchCat(){
        Cursor cursor = db.rawQuery("SELECT * FROM catering", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchA(){
        Cursor cursor = db.rawQuery("SELECT * FROM artist", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchJO(){
        Cursor cursor = db.rawQuery("SELECT * FROM job_offer", null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchIOG(int id){
        Cursor cursor = db.rawQuery("SELECT income,expenses,goal FROM shop WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchCatC(int id){
        Cursor cursor = db.rawQuery("SELECT cost FROM catering WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchAC(int id){
        Cursor cursor = db.rawQuery("SELECT cost FROM artist WHERE id="+id, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void updateNOI(int rid, int n){
        ContentValues contentValues=new ContentValues();
        contentValues.put("guests", n);
        int ret=db.update("reception",contentValues,"id="+rid,null);
    }
    public Cursor fetchRecN(int rid){
        Cursor cursor = db.rawQuery("SELECT guests FROM reception WHERE id="+rid, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
}
