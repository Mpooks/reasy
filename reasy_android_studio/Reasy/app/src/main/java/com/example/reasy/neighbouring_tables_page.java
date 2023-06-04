package com.example.reasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class neighbouring_tables_page extends AppCompatActivity {
    private LinearLayout linearLayout;
    private int id,rid,wid,tid,w;
    private TextView b;
    private String date;
    private ArrayList<reservation> rlist=new ArrayList<>();
    private ArrayList<shop> sl=new ArrayList<>();
    private ArrayList<waiter> wlist=new ArrayList<>();
    private  ArrayList<Integer> tl=new ArrayList<>(),rl=new ArrayList<>(),wl=new ArrayList<>(),nt=new ArrayList<>(),ch=new ArrayList<>();
    public void chooseTablesAndValidate(View view){
        waiter.createAssessment(neighbouring_tables_page.this, rid, wid,ch,date,w);
        popupMessage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbouring_tables_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        date=bundle.getString("date");
        tl=bundle.getIntegerArrayList("tables");
        rl=bundle.getIntegerArrayList("reservations");
        tid=bundle.getInt("tid");
        rid=bundle.getInt("rid");
        wl=bundle.getIntegerArrayList("waiters");
        wid=bundle.getInt("wid");
        w=bundle.getInt("w");
        nt=bundle.getIntegerArrayList("nt");
        sl=shop.getShops(neighbouring_tables_page.this);
        linearLayout = findViewById(R.id.linear_layout);

        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.tb5);
                b.setText(String.valueOf(user.getBalance(neighbouring_tables_page.this,id))+"\u20AC");
            }
        }
        if (nt.contains(0)) {
            TextView tv = new TextView(this);
            tv.setText("There are no neighboring tables without a waiter.");
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
            for (int i : nt) {
                CheckBox tv = new CheckBox(this);
                Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
                tv.setTextSize(18);
                tv.setHeight(192);
                tv.setWidth(966);
                tv.setPadding(30, 90, 30, 90);
                tv.setId(i);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.pt);
                tv.setText("Table id: " + i);
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setTypeface(typeface);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ch.add(i);
                    }
                });

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 0, 20, 30);
                tv.setLayoutParams(lp);

                linearLayout.addView(tv);
            }
        }
    }

    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made the assessment!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(neighbouring_tables_page.this,shop_main_page.class);
                Bundle b = new Bundle();
                //Add your data to bundle
                b.putInt("id", id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void goBack(View v) {
        if(w==0) {
            Intent intent = new Intent(this, table_reservations_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putString("date", date);
            b.putIntegerArrayList("tables", tl);
            b.putIntegerArrayList("reservations", rl);
            b.putInt("tid", tid);
            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, available_waiters_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putString("date", date);
            b.putIntegerArrayList("tables", tl);
            b.putIntegerArrayList("reservations", rl);
            b.putInt("tid", tid);
            b.putInt("rid", rid);
            b.putIntegerArrayList("waiters", tl);

            intent.putExtras(b);
            startActivity(intent);
        }
    }
}