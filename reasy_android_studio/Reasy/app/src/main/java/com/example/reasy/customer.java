package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class customer extends user{
    private int points;
    private ArrayList<customer> friend_list = new ArrayList<customer>();
    private ArrayList<reception> receptions = new ArrayList<reception>();
    private ArrayList<calendar> calendar = new ArrayList<calendar>();
    private int num_of_reservations;
    private ArrayList<order> order_history = new ArrayList<order>();
    private ArrayList<rating> rating_history = new ArrayList<rating>();
    private ArrayList<order> rated_order_history = new ArrayList<order>();

    public customer(String email, String password,String name, int id, double balance, int points, int num_of_reservations) {
        super(email,password,name, id, balance);
        this.points = points;
        this.num_of_reservations = num_of_reservations;
    }

    public ArrayList<order> getOrderHistory(){
        return order_history;
    }
    public ArrayList<reception> getReceptions(){
        return receptions;
    }
    public ArrayList<customer> getFriendList(){
        return friend_list;
    }
    public ArrayList<calendar> getCalendar(){
        return calendar;
    }
    public ArrayList<rating> getRatingHistory(){
        return rating_history;
    }
    public ArrayList<order> getOrderRatingHistory(){
        return rated_order_history;
    }
    public void addToOrderHistory(order newor){
        order_history=new ArrayList<>();
        order_history.add(newor);
    }
    public void addPointsRShop(int p){
        points=points+p;
    }
    public void addPointsROrder(int p, order o){
        points=points+p;
        rated_order_history.add(o);
    }
    public int getNumOfReservations(){
        return num_of_reservations;
    }
    public void updateNumOfReservations(){
        num_of_reservations++;
    }
    public void updateReception(int new_num, int rec_id){
        for(int i=0;i<receptions.size();i++){
            int j= (receptions.get(i)).getReception_id();
            if(rec_id==j){
                receptions.get(i).changeReception(new_num);
            }
        }
    }
    public void addtoCustomer(reception new_rec){
        receptions.add(new_rec);
    }
    public void addtoCalendar(calendar event){
        calendar.add(event);
    }
    public void save(rating rate){
        rating_history.add(rate);
    }

    public void setFriend_list(ArrayList<customer> friend_list) {
        this.friend_list = friend_list;
    }

    public int getPoints() {
        return points;
    }
    public static ArrayList<customer> getCustomer(Context c){
        try {
            DatabaseManager dbm = new DatabaseManager(c);
            dbm.open();
            Cursor cursor=dbm.fetchC();
            ArrayList<customer> cl = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    cl.add(new customer(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(0),cursor.getDouble(4),cursor.getInt(5),cursor.getInt(6)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.open();
            return cl;
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }
}
