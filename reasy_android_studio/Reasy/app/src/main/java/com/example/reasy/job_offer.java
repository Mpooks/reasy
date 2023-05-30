package com.example.reasy;
import java.util.ArrayList;
public class job_offer {

    private int shop_id;
    private String position;
    private double salary;
    private double experience;
    private String start_date;
    private String end_date;
    private int id;

    public job_offer(int id, int shop_id, String position, double salary, double experience, String start_date, String end_date) {
        this.id=id;
        this.shop_id = shop_id;
        this.position = position;
        this.salary = salary;
        this.experience = experience;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public double getSalary(){
        return salary;
    }
}
