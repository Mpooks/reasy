package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class job_offer_form_page extends AppCompatActivity {

    private int id;
    private String selectedJO;
    private ArrayList<job_offer> jl=new ArrayList<>();
    private double[] iog=new double[3];
    private double mj;
    private TextView text,sal;

    public void fillForm(View v){
            text.setText(" ");
            jl=job_offer.getJO(job_offer_form_page.this);
            mj=job_offer.getMedianSalary(jl,selectedJO);
            sal.setText(""+mj);
    }
    public void show(View v){
        if(selectedJO.compareTo("choose")==0){
            text.setText("You must pick a position first.");
        }
        else{
            iog=shop.getInOutGoal(job_offer_form_page.this,id);
            Intent intent=new Intent(this,set_salary_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putString("position",selectedJO);
            b.putDoubleArray("iog",iog);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_form_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        text = findViewById(R.id.jo);
        sal=findViewById(R.id.medsal);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Waiter");
        arrayList.add("Bartender");
        arrayList.add("Barista");
        arrayList.add("Cashier");
        arrayList.add("Dishwasher");
        arrayList.add("Sous-Chef");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedJO = parent.getItemAtPosition(position).toString();
                fillForm(view);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                selectedJO="choose";
            }
        });
    }

    public void goBack(View v){
        Intent intent=new Intent(this,shop_main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}