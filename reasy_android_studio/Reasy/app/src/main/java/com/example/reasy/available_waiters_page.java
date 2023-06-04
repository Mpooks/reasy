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

public class available_waiters_page extends AppCompatActivity {
    private LinearLayout linearLayout;
    private int id,rid,wid,tid;
    private TextView b;
    private String date;
    private ArrayList<reservation> rlist=new ArrayList<>();
    private ArrayList<shop> sl=new ArrayList<>();
    private ArrayList<waiter> wlist=new ArrayList<>();
    private  ArrayList<Integer> tl=new ArrayList<>(),rl=new ArrayList<>(),wl=new ArrayList<>(),nt=new ArrayList<>();

    public void chooseWaiter(int wid){
        nt=table.getNeighbouringTables(available_waiters_page.this,tid,date);
        if(nt.isEmpty()){
            nt.add(0);
        }
        show();
    }
    public void show(){
            Intent intent = new Intent(available_waiters_page.this, neighbouring_tables_page.class);
            //Create the bundle
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putString("date", date);
            b.putIntegerArrayList("tables", tl);
            b.putIntegerArrayList("reservations", rl);
            b.putInt("wid", wid);
            b.putInt("rid",rid);
            b.putInt("tid",tid);
            b.putIntegerArrayList("waiters", wl);
            b.putIntegerArrayList("nt",nt);
            b.putInt("w",1);

            intent.putExtras(b);
            startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_waiters_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        date=bundle.getString("date");
        tl=bundle.getIntegerArrayList("tables");
        rl=bundle.getIntegerArrayList("reservations");
        tid=bundle.getInt("tid");
        rid=bundle.getInt("rid");
        wl=bundle.getIntegerArrayList("waiters");
        sl=shop.getShops(available_waiters_page.this);
        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.tb4);
                b.setText(String.valueOf(user.getBalance(available_waiters_page.this,id))+"\u20AC");
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        if (wl.contains(0)) {
            TextView tv = new TextView(this);
            tv.setText("There are no available waiters.");
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
            for (int i : wl) {
                wlist.add(waiter.getWD(available_waiters_page.this, i));
            }
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            for (waiter o : wlist) {
                TextView tv = new TextView(this);
                tv.setText("Waiter Id: " + o.getWaiter_id() + "    Name: " + o.getName());
                tv.setId(o.getWaiter_id());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wid = o.getWaiter_id();
                        chooseWaiter(wid);
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
        Intent intent=new Intent(this,table_reservations_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putString("date",date);
        b.putIntegerArrayList("tables",tl);
        b.putIntegerArrayList("reservations",rl);
        b.putInt("tid",tid);
        intent.putExtras(b);
        startActivity(intent);
    }
}