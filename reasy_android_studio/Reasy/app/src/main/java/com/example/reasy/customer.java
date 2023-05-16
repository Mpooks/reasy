package com.example.reasy;

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

    public customer(String name, int id, float balance, ArrayList<reservation> reservations, int points, ArrayList<customer> friend_list, ArrayList<reception> receptions, ArrayList<calendar> calendar, int num_of_reservations, ArrayList<order> order_history, ArrayList<rating> rating_history, ArrayList<order> rated_order_history) {
        super(name, id, balance, reservations);
        this.points = points;
        this.friend_list = friend_list;
        this.receptions = receptions;
        this.calendar = calendar;
        this.num_of_reservations = num_of_reservations;
        this.order_history = order_history;
        this.rating_history = rating_history;
        this.rated_order_history = rated_order_history;
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
        order_history.add(newor);
    }
    public void addPoints(int p){
        points=points+p;
    }
    public int getNumOfReservations(){
        return num_of_reservations;
    }
    public void updateNumOfReservations(){
        num_of_reservations++;
    }
    public void updateReception(int newnum){

    }
    public void addtoCustomer(){

    }
    public void save(){

    }
}
