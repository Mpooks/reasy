package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class customer extends user {
    private int points;
    private int num_of_reservations;

    public customer(String email, String password, String name, int id, double balance, int points, int num_of_reservations) {
        super(email, password, name, id, balance);
        this.points = points;
        this.num_of_reservations = num_of_reservations;
    }

    public void addPointsRShop(int p) {
        points = points + p;
    }

    public void addPointsROrder(int p, order o) {
        points = points + p;
    }

    public static int getNumOfReservations(Context c, int id) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            int num;
            Cursor cursor = dbm.fetchNumRes(id);
            num = cursor.getInt(0);
            dbm.close();
            return num;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static int updateNumOfReservations(Context c, int cid, int nor) {
        int num_of_reservations = nor + 1;
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            int ret;
            ret = dbm.updateCRes(cid, num_of_reservations);
            dbm.close();
            return ret;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPoints() {
        return points;
    }

    public static ArrayList<customer> getCustomer(Context c) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor = dbm.fetchC();
            ArrayList<customer> cl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    cl.add(new customer(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(0), cursor.getDouble(4), cursor.getInt(5), cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return cl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<reservation> getReservations(Context c, int cid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor = dbm.fetchCustRes(cid);
            ArrayList<reservation> rl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    rl.add(new reservation(cursor.getInt(1), cursor.getInt(2), cursor.getInt(0), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return rl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<order> getOrderHistory(Context c, int cid, int sid) {
        ArrayList<order> o = new ArrayList<>();
        o = order.getOrder(c, cid, sid);
        return o;
    }

    public static ArrayList<reception> getReceptions(Context c, int cid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor = dbm.fetchCustRec(cid);
            ArrayList<reception> rl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    rl.add(new reception(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return rl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Integer> getFriendList(Context c, int cid,int rid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();

            ArrayList<Integer> rl = new ArrayList<>();
            rl = dbm.fetchFL(cid,rid);

            return rl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<order> getOrderAndRatingHistory(Context c, int cid, int sid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor1 = dbm.fetchRH(cid,sid);
            ArrayList<rating> rl = new ArrayList<>();

            if (cursor1.moveToFirst()) {
                do {
                    rl.add(new rating(cursor1.getInt(0), cursor1.getInt(1), cursor1.getDouble(2)));
                } while (cursor1.moveToNext());
            }
                if (rl.isEmpty()) {
                    dbm.updateP(cid, 0);
                }

            cursor1.close();

            Cursor cursor=dbm.fetchOrder(cid,sid);
            ArrayList<order> o = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    o.add(new order(cursor.getInt(2),cursor.getInt(0),cursor.getInt(1),cursor.getDouble(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
            return o;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Integer> getRatedOrders(Context c, int cid, int oid) {
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor = dbm.fetchRO(cid,oid);
            ArrayList<Integer> rl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    rl.add(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            if(rl.isEmpty()){
                dbm.updateP(cid,1);
            }

            cursor.close();
            dbm.close();
            return rl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Integer> getAv(Context c,ArrayList<Integer> in,String d){
        int av;
        ArrayList cl=new ArrayList<>();
        for(int cus:in){
            av=calendar.getAv(c,cus,d);
            if(av==1){
                cl.add(cus);
            }
        }
        return cl;
    }

}


