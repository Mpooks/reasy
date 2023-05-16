package com.example.reasy;

import java.util.ArrayList;

public class table {

    private int table_id;

    private int capacity;

    private int shop_id;

    private ArrayList<reservation> reservations=new ArrayList<reservation>();

    private ArrayList<Integer> neighbouring_tables = new ArrayList<Integer>();

    public table(int table_id, int capacity, int shop_id, ArrayList<reservation> reservations, ArrayList<Integer> neighbouring_tables) {
        this.table_id = table_id;
        this.capacity = capacity;
        this.shop_id = shop_id;
        this.reservations = reservations;
        this.neighbouring_tables = neighbouring_tables;
    }

    public int[] getCapacity(int n){
        int[] res=new int[2];
        if(n<=capacity){
            res[0]=1;
        }
        else{
            res[0]=0;
        }
        res[1]=table_id;
        return res;
    }

    public ArrayList<reservation> getReservations(){
        return reservations;
    }

    public ArrayList<Integer> getNeighbouringTables(){
        return neighbouring_tables;
    }

    public void saveToTable(reservation new_r){
        reservations.add(new_r);
    }

    public void updateTable(int res_id, int waiter_id){
        for(int i=0;i<reservations.size();i++){
            int j= (reservations.get(i)).getReservation_id();
            if(res_id==j){
                reservations.get(i).setWaiterToRes(waiter_id);
            }
        }
    }
}
