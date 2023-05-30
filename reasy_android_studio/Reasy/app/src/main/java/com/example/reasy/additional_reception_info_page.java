package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class additional_reception_info_page extends AppCompatActivity {
    private LinearLayout mg,ct;
    private int id,g,car;
    private double bal,cost;
    private String date;
    private String genre,type;
    private TextView p,b;
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<catering> c=new ArrayList<>();
    private ArrayList<artist> a=new ArrayList<>();
    private ArrayList<Integer> ar_r=new ArrayList<>(),a_r=new ArrayList<>(),c_r=new ArrayList<>();
    private ArrayList<String> ar_n=new ArrayList<>(),a_n=new ArrayList<>(),c_n=new ArrayList<>();
    private TextView w6,w7,w4;
    public void chooseReceptionAndCuisineType(View v){
        if(w6.getText().toString().length()==0 || w7.getText().toString().length()==0){
            w4.setText("You have to choose cuisine type and music genre.");
        }
        else{
            DatabaseManager dbm = new DatabaseManager(additional_reception_info_page.this);
            try {
                dbm.open();
                Cursor cursor=dbm.fetchCat();
                if (cursor.moveToFirst()) {
                    do {
                        c.add(new catering(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getString(3)));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Cursor cu=dbm.fetchA();
                if (cu.moveToFirst()) {
                    do {
                        a.add(new artist(cu.getInt(0),cu.getString(1),cu.getDouble(2),cu.getString(3)));
                    } while (cu.moveToNext());
                }
                cu.close();
                dbm.close();
                for(catering cat:c){
                    if(cat.getCuisineType().compareTo(type)==0){
                        c_r.add(cat.getCatering());
                        c_n.add(cat.getName());
                    }
                }
                for(artist ar:a){
                    if(ar.getMusicGenre().compareTo(genre)==0){
                        a_r.add(ar.getArtist());
                        a_n.add(ar.getName());
                    }
                }
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
            show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_reception_info_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        g= bundle.getInt("g");
        date= bundle.getString("date");
        ar_r= bundle.getIntegerArrayList("ar_r");
        ar_n=bundle.getStringArrayList("ar_n");
        car=bundle.getInt("car");
        cost=bundle.getInt("cost");
        cl=customer.getCustomer(additional_reception_info_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView52);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView53);
                b.setText(String.valueOf(user.getBalance(additional_reception_info_page.this,id))+"\u20AC");
            }
        }

        ct = findViewById(R.id.ct);
        mg = findViewById(R.id.mg);
        w6 = findViewById(R.id.warning6);
        w7 = findViewById(R.id.warning4);
        w4 = findViewById(R.id.warning7);

        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
        tv.setText("pop");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre="pop";
                w6.setText("Chosen music genre:"+genre);
            }
        });
        tv.setTextSize(18);
        tv.setHeight(120);
        tv.setWidth(966);
        tv.setPadding(30, 90, 30, 90);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundResource(R.drawable.menu_item);
        tv.setTypeface(typeface);
        tv.setLayoutParams(lp);
        mg.addView(tv);

        TextView tv1 = new TextView(this);
        tv1.setText("trap");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre="trap";
                w6.setText("Chosen music genre:"+genre);
            }
        });
        tv1.setTextSize(18);
        tv1.setHeight(120);
        tv1.setWidth(966);
        tv1.setPadding(30, 90, 30, 90);
        tv1.setGravity(Gravity.CENTER);
        tv1.setBackgroundResource(R.drawable.menu_item);
        tv1.setTypeface(typeface);
        tv1.setLayoutParams(lp);
        mg.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText("rock");
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre="rock";
                w6.setText("Chosen music genre:"+genre);
            }
        });
        tv2.setTextSize(18);
        tv2.setHeight(120);
        tv2.setWidth(966);
        tv2.setPadding(30, 90, 30, 90);
        tv2.setGravity(Gravity.CENTER);
        tv2.setBackgroundResource(R.drawable.menu_item);
        tv2.setTypeface(typeface);
        tv2.setLayoutParams(lp);
        mg.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText("metal");
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre="metal";
                w6.setText("Chosen music genre:"+genre);
            }
        });
        tv3.setTextSize(18);
        tv3.setHeight(120);
        tv3.setWidth(966);
        tv3.setPadding(30, 90, 30, 90);
        tv3.setGravity(Gravity.CENTER);
        tv3.setBackgroundResource(R.drawable.menu_item);
        tv3.setTypeface(typeface);
        tv3.setLayoutParams(lp);
        mg.addView(tv3);

        TextView tv4 = new TextView(this);
        tv4.setText("jazz");
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre="jazz";
                w6.setText("Chosen music genre:"+genre);
            }
        });
        tv4.setTextSize(18);
        tv4.setHeight(120);
        tv4.setWidth(966);
        tv4.setPadding(30, 90, 30, 90);
        tv4.setGravity(Gravity.CENTER);
        tv4.setBackgroundResource(R.drawable.menu_item);
        tv4.setTypeface(typeface);
        tv4.setLayoutParams(lp);
        mg.addView(tv4);

        TextView tv5 = new TextView(this);
        tv5.setText("Asian");
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Asian";
                w7.setText("Chosen cuisine type:"+type);
            }
        });
        tv5.setTextSize(18);
        tv5.setHeight(120);
        tv5.setWidth(966);
        tv5.setPadding(30, 90, 30, 90);
        tv5.setGravity(Gravity.CENTER);
        tv5.setBackgroundResource(R.drawable.menu_item);
        tv5.setTypeface(typeface);
        tv5.setLayoutParams(lp);
        ct.addView(tv5);

        TextView tv6 = new TextView(this);
        tv6.setText("Italian");
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Italian";
                w7.setText("Chosen cuisine type:"+type);
            }
        });
        tv6.setTextSize(18);
        tv6.setHeight(120);
        tv6.setWidth(966);
        tv6.setPadding(30, 90, 30, 90);
        tv6.setGravity(Gravity.CENTER);
        tv6.setBackgroundResource(R.drawable.menu_item);
        tv6.setTypeface(typeface);
        tv6.setLayoutParams(lp);
        ct.addView(tv6);

        TextView tv7 = new TextView(this);
        tv7.setText("Grill");
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Grill";
                w7.setText("Chosen cuisine type:"+type);
            }
        });
        tv7.setTextSize(18);
        tv7.setHeight(120);
        tv7.setWidth(966);
        tv7.setPadding(30, 90, 30, 90);
        tv7.setGravity(Gravity.CENTER);
        tv7.setBackgroundResource(R.drawable.menu_item);
        tv7.setTypeface(typeface);
        tv7.setLayoutParams(lp);
        ct.addView(tv7);

        TextView tv8 = new TextView(this);
        tv8.setText("Bar");
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Bar";
                w7.setText("Chosen cuisine type:"+type);
            }
        });
        tv8.setTextSize(18);
        tv8.setHeight(120);
        tv8.setWidth(966);
        tv8.setPadding(30, 90, 30, 90);
        tv8.setGravity(Gravity.CENTER);
        tv8.setBackgroundResource(R.drawable.menu_item);
        tv8.setTypeface(typeface);
        tv8.setLayoutParams(lp);
        ct.addView(tv8);
    }

    public void show(){
        Intent intent = new Intent(additional_reception_info_page.this, reception_options_page.class);

        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putString("date", date);
        b.putIntegerArrayList("ar_r", ar_r);
        b.putInt("g", g);
        b.putStringArrayList("ar_n", ar_n);
        b.putInt("car",car);
        b.putDouble("cost",cost);
        b.putIntegerArrayList("a_r", a_r);
        b.putStringArrayList("a_n", a_n);
        b.putIntegerArrayList("c_r", c_r);
        b.putStringArrayList("c_n", c_n);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void goBack(View v){
        Intent intent = new Intent(additional_reception_info_page.this, reception_area_page.class);

        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putString("date", date);
        b.putIntegerArrayList("ar_r", ar_r);
        b.putInt("g", g);
        b.putStringArrayList("ar_n", ar_n);
        bal=user.getBalance(additional_reception_info_page.this,id);
        DatabaseManager dbm = new DatabaseManager(additional_reception_info_page.this);
        try {
            dbm.open();
            Cursor c=dbm.fetchRAfromID(car);
            cost=c.getDouble(0);
            user.setBalance(additional_reception_info_page.this,id,bal+cost);
            dbm.close();

        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        intent.putExtras(b);
        startActivity(intent);
    }
}