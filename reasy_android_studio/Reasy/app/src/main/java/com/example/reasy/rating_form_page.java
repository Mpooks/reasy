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

    private int id,sid,numofr,cn;
    private int rated = 0;
    private double rate;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> clist=new ArrayList<>();
    private ArrayList<rating> ratingh;
    private ArrayList<String> oh;
    private TextView text3,text4,text5,text6,text7,text8;
    private LinearLayout linearLayout;
    private menu m;
    private ArrayList<product_menu> arrayList;
    private main_lists ml;
    private shop chosens;
    private customer cu;
    private String n,a,p;
    public rating_form_page() {

    }
    public void chooseShopAndFillRating(){
       // ml= main_lists.createLists();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        clist = (ArrayList<customer>) ml.getCustomer_list().clone();
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        for(shop s: slist){
            if(s.getId()==sid){
               /* n=s.getName();
                a=s.getAddress();
                p=s.getPhone();
                m = s.getShop_m();
                numofr=s.getNumofrates();
                rate=s.getRating();
                arrayList=(ArrayList<product_menu>) m.getProducts().clone();
                oh=(ArrayList<String>) s.getOpeningHours();
                chosens=s;*/
            }

        }

        for (customer c : clist) {
            if (c.getId() == id) {
                cn = c.getPoints();
                ratingh = (ArrayList<rating>) c.getRatingHistory().clone();
                cu = c;
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
        text7 = findViewById(R.id.textView7);
        text8 = findViewById(R.id.textView13);
        text8.setText(cn+"pts");
        RatingBar ratingBar;
        Button btSubmit;

        ratingBar = findViewById(R.id.ratingBar);
        btSubmit = findViewById(R.id.rate);



        btSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double a = (double)ratingBar.getRating();
                chosens.changeRating(a);
                rate = chosens.getRating();
                rate = Math.round(rate*10.0)/10.0;
                numofr=chosens.getNumofrates();
                text6.setText(rate+ " ("+numofr+")");
                if(ratingh.isEmpty()==true){
                    cu.addPointsRShop(50);
                    text7.setText("You got 50 points!");
                    rated = 1;
                }
                if(ratingh.isEmpty()==false){
                    text7.setText("You got 0 points!");
                    rated = 1;
                }
            }

        });
    }
    public void show(View v){
        if(rated == 0){
            text7.setText("You have to rate our shop to continue.");
        }
        if(rated == 1){
            Intent intent=new Intent(this,rating_previous_orders_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putInt("sid",sid);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form_page);
        chooseShopAndFillRating();
    }

    public void goBack(View v){
        Intent intent=new Intent(this,shop_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}