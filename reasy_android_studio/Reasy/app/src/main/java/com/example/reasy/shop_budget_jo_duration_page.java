package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class shop_budget_jo_duration_page extends AppCompatActivity {

    private TextView t1;
    private int id,foundm=0;
    private double salary,budget;
    private double[] iog=new double[3];
    private String position;
    private int stay,stam,stad,endy,endm,endd;
    private EditText sty,stm,std,eny,enm,end;
    private CheckBox oc,ipc;

    public void fillDate() {
        if (!(oc.isChecked()) && (!(ipc.isChecked()))) {
            foundm = 1;
            t1.setText("You must choose one of the options.");
        } else if ((oc.isChecked()) && ((ipc.isChecked()))) {
            foundm = 1;
            t1.setText("You must choose only one.");
        }
        if(foundm==0){
            stay=Integer.valueOf(sty.getText().toString());
            stam=Integer.valueOf(sty.getText().toString());
            stad=Integer.valueOf(sty.getText().toString());
            endy=Integer.valueOf(sty.getText().toString());
            endm=Integer.valueOf(sty.getText().toString());
            endd=Integer.valueOf(sty.getText().toString());
            
        }
    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_budget_jo_duration_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        iog=bundle.getDoubleArray("iog");
        position=bundle.getString("position");
        salary=bundle.getDouble("salary");
        budget=bundle.getDouble("budget");
        oc=findViewById(R.id.y);
        ipc=findViewById(R.id.n);
        t1=findViewById(R.id.name9);
        sty=findViewById(R.id.sy);
        stm=findViewById(R.id.sm);
        std=findViewById(R.id.sd);
        eny=findViewById(R.id.ey);
        enm=findViewById(R.id.em);
        end=findViewById(R.id.ed);
    }
    public void show(View v){
            Intent intent=new Intent(this,set_salary_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putString("position",position);
            b.putDoubleArray("iog",iog);
            intent.putExtras(b);
            startActivity(intent);
        }
}