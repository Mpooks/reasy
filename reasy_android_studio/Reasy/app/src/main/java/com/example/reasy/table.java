package com.example.reasy;

import java.util.ArrayList;

public class table {

    private int table_id;

    private int capacity;

    private int shop_id;

    private reservation reservations;

    private ArrayList<Integer> neighbouring_tables = new ArrayList<Integer>();

    public table(int table_id, int capacity, int shop_id, reservation reservations, ArrayList<Integer> neighbouring_tables) {
        this.table_id = table_id;
        this.capacity = capacity;
        this.shop_id = shop_id;
        this.reservations = reservations;
        this.neighbouring_tables = neighbouring_tables;
    }

    public void getCapacityAndReservations(){

    }

    public void getReservations(){

    }

    public void getNeighbouringTables(){

    }

    public void saveToTable(){

    }

    public void updateTable(){

    }
}
