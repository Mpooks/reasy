package com.example.reasy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class rating_form_page extends AppCompatActivity {

    private int id,sid,numofr;
    private double rate;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<String> oh;
    private TextView text3,text4,text5,text6;
    private LinearLayout linearLayout;
    private menu m;
    private ArrayList<product_menu> arrayList;
    private main_lists ml;
    private shop chosens;
    private String n,a,p;
    public rating_form_page() {

    }
    public void chooseShopAndFillRating(){
            ml= main_lists.createLists();
            slist = (ArrayList<shop>) ml.getShop_list().clone();
            Bundle bundle = getIntent().getExtras();
            id= bundle.getInt("id");
            sid= bundle.getInt("sid");
            for(shop s: slist){
                if(s.getId()==sid){
                    n=s.getName();
                    a=s.getAddress();
                    p=s.getPhone();
                    m = s.getShop_m();
                    numofr=s.getNumofrates();
                    rate=s.getRating();
                    arrayList=(ArrayList<product_menu>) m.getProducts().clone();
                    oh=(ArrayList<String>) s.getOpeningHours();
                    chosens=s;
                }
            }
            linearLayout = findViewById(R.id.linear_layout);
            text3 = findViewById(R.id.textView3);
            text3.setText(n);
            text4 = findViewById(R.id.textView4);
            text4.setText("Address: "+a);
            text5 = findViewById(R.id.textView5);
            text5.setText("Telephone: "+p);
            text6 = findViewById(R.id.rateg);
            text6.setText(rate+ " ("+numofr+")");

        RatingBar ratingBar;
        Button btSubmit;

        ratingBar = findViewById(R.id.ratingBar);
        btSubmit = findViewById(R.id.rate);

        btSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double a = (double)ratingBar.getRating();
                chosens.changeRating(a);

            }
        });
    }
    public void show(){
        Intent intent=new Intent(this,rating_previous_orders_page.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form_page);
        chooseShopAndFillRating();
    }

    public void goBack(View v){
        Intent intent=new Intent(this,search_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}