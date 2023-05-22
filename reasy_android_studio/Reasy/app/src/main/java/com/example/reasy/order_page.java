package com.example.reasy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class order_page extends AppCompatActivity {
    private int id,sid,rid;
    private String sname;
    private main_lists ml;
    private LinearLayout linearLayout;
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<reservation> rl=new ArrayList<reservation>();
    public void createOrder(){

    }
    public void create(){

    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        linearLayout = findViewById(R.id.linear_layout);
        ml= main_lists.createLists();
        ul = (ArrayList<user>) ml.getUser_list().clone();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        for(user u: ul) {
            if(u.getId()==id){
                rl=u.getReservations();
            }
        }
        for(reservation r: rl)
        {
            TextView tv = new TextView(this);
            sid=r.getShop_id();
            for(shop s: slist){
                if(s.getId()==sid){
                    sname=s.getName();
                }
            }
            tv.setText("Reservation id: "+r.getReservation_id()+"\nShop: "+sname);
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(r.getReservation_id());
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.menu_item);
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            tv.setTypeface(typeface);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            linearLayout.addView(tv);
        }
    }
}