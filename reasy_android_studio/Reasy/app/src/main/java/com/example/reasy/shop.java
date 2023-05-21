package com.example.reasy;

import java.util.ArrayList;

public class shop extends user{
    private String address;
    private String city;
    private ArrayList<String> opening_hours = new ArrayList<String>();
    private int capacity;
    private int numofrates;
    private String cuisine_type;
    private double income;
    private double expenses;
    private double goal;
    private double rating;
    private String phone;
    private ArrayList<table> table_list = new ArrayList<table>();
    private ArrayList<supply> supply_history = new ArrayList<supply>();
    private menu shop_m;

    public shop(String email, String password,String name, int id, double balance, ArrayList<reservation> reservations, String address, String city, ArrayList<String> opening_hours, int capacity, int numofrates, String cuisine_type, double income, double expenses, double goal, double rating, ArrayList<table> table_list, ArrayList<supply> supply_history, String phone, menu shop_m) {
        super(email,password,name, id, balance, reservations);
        this.address = address;
        this.city=city;
        this.opening_hours = opening_hours;
        this.capacity = capacity;
        this.numofrates = numofrates;
        this.cuisine_type = cuisine_type;
        this.income = income;
        this.expenses = expenses;
        this.goal = goal;
        this.rating = rating;
        this.table_list = table_list;
        this.supply_history = supply_history;
        this.phone = phone;
        this.shop_m=shop_m;
    }

    public String getPhone(){
        return phone;
    }

    public menu getShop_m() {
        return shop_m;
    }

    public ArrayList<supply> getSupplyHistory(){
        return supply_history;
    }
    public ArrayList<String> getOpeningHours(){
        return opening_hours;
    }
    public int getCapacity(){
        return capacity;
    }
    public ArrayList<table> getTables(){
        return table_list;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public double[] getInOutGoal(){
        double[] res=new double[3];
        res[0]=income;
        res[1]=expenses;
        res[2]=goal;
        return res;
    }
    public void addToSupplyHistory(supply newsupply){
        supply_history.add(newsupply);
    }
    public void changeRating(double newr){
        double r=numofrates*rating;
        numofrates++;
        rating=(rating+newr)/numofrates;
    }
}
