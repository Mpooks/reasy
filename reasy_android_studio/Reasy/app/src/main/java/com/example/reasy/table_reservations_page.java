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

public class table_reservations_page extends AppCompatActivity {
    private LinearLayout linearLayout;
    private int id,rid,wid,tid;
    private TextView b;
    private String date;
    private ArrayList<reservation> rlist=new ArrayList<>();
    private ArrayList<shop> sl=new ArrayList<>();
    private ArrayList<waiter> wlist=new ArrayList<>();
    private  ArrayList<Integer> tl=new ArrayList<>(),rl=new ArrayList<>(),wl=new ArrayList<>(),nt=new ArrayList<>();
    public void chooseReservation(int rid){
        wid=reservation.getWaiterId(table_reservations_page.this,rid);
        wlist=waiter.getWaiters(table_reservations_page.this,id);
        if(wlist.isEmpty()){
            wl.add(0);
        }
        else {
            for (waiter r : wlist) {
                wl.add(r.getWaiter_id());
            }
        }
        show();
    }
    public void show(){
        if(wid==0) {
            Intent intent = new Intent(table_reservations_page.this, available_waiters_page.class);
            //Create the bundle
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putString("date", date);
            b.putIntegerArrayList("tables", tl);
            b.putIntegerArrayList("reservations", rl);
            b.putInt("wid", wid);
            b.putIntegerArrayList("waiters", wl);
            b.putInt("rid",rid);
            b.putInt("tid",tid);

            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            nt=table.getNeighbouringTables(table_reservations_page.this,tid,date);
            if(nt.isEmpty()){
                nt.add(0);
            }
            Intent intent = new Intent(table_reservations_page.this, neighbouring_tables_page.class);
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
            b.putInt("w",0);

            intent.putExtras(b);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reservations_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        date=bundle.getString("date");
        tl=bundle.getIntegerArrayList("tables");
        rl=bundle.getIntegerArrayList("reservations");
        tid=bundle.getInt("tid");
        sl=shop.getShops(table_reservations_page.this);
        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.tb3);
                b.setText(String.valueOf(user.getBalance(table_reservations_page.this,id))+"\u20AC");
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        if (rl.contains(0)) {
            TextView tv = new TextView(this);
            tv.setText("There are no reservations that day.");
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
            for (int i : rl) {
                rlist.add(reservation.getRD(table_reservations_page.this, i));
            }
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            for (reservation o : rlist) {
                TextView tv = new TextView(this);
                tv.setText("Resrvation Id: " + o.getReservation_id() + "    Number of guests: " + o.getNum_of_customers()+"    Time: "+o.getRTime());
                tv.setId(o.getReservation_id());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rid = o.getReservation_id();
                        chooseReservation(rid);
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
        Intent intent=new Intent(this,table_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putString("date",date);
        b.putIntegerArrayList("tables",tl);
        b.putIntegerArrayList("reservations",rl);
        intent.putExtras(b);
        startActivity(intent);
    }
}