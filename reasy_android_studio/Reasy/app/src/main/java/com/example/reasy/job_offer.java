package com.example.reasy;
import java.util.ArrayList;
public class job_offer {

    private int shop_id;
    private String position;
    private double salary;
    private ArrayList<String> working_hours = new ArrayList<String>();
    private double experience;
    private String start_date;
    private String end_date;
    private String description;

    public job_offer(int shop_id, String position, double salary, ArrayList<String> working_hours, double experience, String start_date, String end_date, String description) {
        this.shop_id = shop_id;
        this.position = position;
        this.salary = salary;
        this.working_hours = working_hours;
        this.experience = experience;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
    }
    public double getSalary(){
        return salary;
    }
}
