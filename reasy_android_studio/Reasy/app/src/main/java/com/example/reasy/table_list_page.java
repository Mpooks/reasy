package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class table_list_page extends AppCompatActivity {
    private LinearLayout linearLayout;
    private int id,tid;
    private TextView b;
    private String date;
    private ArrayList<table> tlist=new ArrayList<>();
    private ArrayList<reservation> rlist=new ArrayList<>();
    private ArrayList<shop> sl=new ArrayList<>();
    private  ArrayList<Integer> tl=new ArrayList<>(),rl=new ArrayList<>();
    public void chooseTable(int tid){
        rlist=table.getReservationsT(table_list_page.this,tid);
        for(reservation r:rlist){
            rl.add(r.getReservation_id());
        }
        show();
    }
    public void show(){
        Intent intent = new Intent(table_list_page.this, table_reservations_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putString("date", date);
        b.putIntegerArrayList("tables", tl);
        b.putIntegerArrayList("reservations", rl);

        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        date=bundle.getString("date");
        tl=bundle.getIntegerArrayList("tables");
        sl=shop.getShops(table_list_page.this);
        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.tb2);
                b.setText(String.valueOf(user.getBalance(table_list_page.this,id))+"\u20AC");
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        if (tl.contains(0)) {
            TextView tv = new TextView(this);
            tv.setText("There are no tables.");
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.pt);
            tv.setTextColor(Color.parseColor("#000000"));
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            tv.setTypeface(typeface);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            linearLayout.addView(tv);
        }else {
            for (int i : tl) {
                tlist.add(table.getTD(table_list_page.this, i));
            }
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            for (table o : tlist) {
                TextView tv = new TextView(this);
                tv.setText("Table Id: " + o.getTable_id() + "    Capacity: " + o.getCapacity());
                tv.setId(o.getTable_id());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tid = o.getTable_id();
                        chooseTable(tid);
                    }
                });
                tv.setTextSize(18);
                tv.setHeight(192);
                tv.setWidth(966);
                tv.setPadding(30, 90, 30, 90);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.menu_item);
                tv.setTypeface(typeface);
                tv.setLayoutParams(lp);

                linearLayout.addView(tv);
            }
        }

    }

    public void goBack(View v) {
        Intent intent=new Intent(this,shop_calendar_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}