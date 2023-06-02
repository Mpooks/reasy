package com.example.reasy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class set_salary_page extends AppCompatActivity {

    private int id;
    private double[] iog=new double[3];
    private String position;
    public void fillSalary(){

    }
    public void calcBudget(){

    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_salary_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");

    }
}