package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class set_salary_page extends AppCompatActivity {

    private int id;
    private double salary,budget;
    private double[] iog=new double[3];
    private ArrayList<shop> sl=new ArrayList<>();
    private String position;
    private TextView t1,t2,t3,w,b;
    private EditText s;
    public void fillSalary(View v){
        salary=Integer.valueOf(s.getText().toString());
        if(salary<=0) {
            w.setText("The salary must be a positive number.");
        }
        else{
           calcBudget();
        }
    }
    public void calcBudget(){
        budget=iog[0]-(iog[1]+salary);
        show();
    }
    public void show(){
        Intent intent=new Intent(this,shop_budget_jo_duration_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putString("position",position);
        b.putDoubleArray("iog",iog);
        b.putDouble("salary",salary);
        b.putDouble("budget",budget);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_salary_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        iog=bundle.getDoubleArray("iog");
        position=bundle.getString("position");
        t1=findViewById(R.id.in);
        t2=findViewById(R.id.ex);
        t3=findViewById(R.id.g);
        w=findViewById(R.id.name10);
        sl=shop.getShops(set_salary_page.this);
        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.textView42);
                b.setText(String.valueOf(user.getBalance(set_salary_page.this,id))+"\u20AC");
            }
        }

        s=findViewById(R.id.sal);

        t1.setText(""+iog[0]);
        t2.setText(""+iog[1]);
        t3.setText(""+iog[2]);

    }
    public void goBack(View v){
        Intent intent=new Intent(this,job_offer_form_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}