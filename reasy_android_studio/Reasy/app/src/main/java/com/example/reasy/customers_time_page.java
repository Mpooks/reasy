package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class customers_time_page extends AppCompatActivity {
    private int id,sid,g,foundp=0,foundt=0,c,tid,notav=0;
    private ArrayList<String> oh;
    private ArrayList<reservation> r;
    private ArrayList<table> tablel=new ArrayList<>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private TextView text3,text4,text5,po,b;
    private EditText p,t;
    private String n,dateb,tor;

    public void chooseNumAndTime(View view) {
        p = findViewById(R.id.editTextNumber);
        t = findViewById(R.id.editTextTime);
        if (p.getText().toString().length() == 0) {
            foundp = 1;
            text5.setText("Invalid format.");
        } else if (Integer.valueOf(p.getText().toString()) <= 0) {
            foundp = 1;
            text5.setText("Invalid choice.");
        } else {
            text5.setText("");
            foundp = 0;
        }
        int count = 0;

        for (int i = 0; i < t.getText().toString().length(); i++) {
            if (t.getText().toString().charAt(i) == ':') {
                count++;
            }
        }
        if (count == 1 && !(t.getText().toString().endsWith(":"))) {
            String str = t.getText().toString();
            String parts[] = str.split(":");
            String string1 = parts[0];
            String string2 = parts[1];
            if (string1 == null || string1.length() == 0 || string2 == null || string2.length() == 0) {
                foundt = 1;
                text4.setText("Invalid format.");
            } else {
                if (((Integer.valueOf(string1) <= 23) && (Integer.valueOf(string1) >= 0)) && ((Integer.valueOf(string2) <= 59) && (Integer.valueOf(string2) >= 0))) {
                    if (string2.length() < 2) {
                        string2 = "0".concat(string2);
                        tor = string1 + ":" + string2;
                    }
                    foundt = 0;
                    text4.setText("");
                } else {
                    foundt = 1;
                    text4.setText("Invalid time.");
                }
            }
        } else {
            foundt = 1;
            text4.setText("Invalid format.");
        }
        if ((foundp == 0) && (foundt == 0)) {
            g = Integer.valueOf(p.getText().toString());
            tor = t.getText().toString();
            tablel = shop.getTables(customers_time_page.this, sid, g, dateb, tor);
            if (tablel.isEmpty()) {
                text4.setText("There are no available tables for your choice.");
            } else {
                c=tablel.get(0).getCapacity();
                tid=tablel.get(0).getTable_id();
                for(table ta: tablel){
                    if (ta.getCapacity() < c) {
                        c = ta.getCapacity();
                        tid=ta.getTable_id();
                    }
                }
                show();
            }
        }
    }
    public void show(){
        Intent intent = new Intent(this, requests_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putInt("sid", sid);
        b.putString("date", dateb);
        b.putStringArrayList("open", oh);
        b.putInt("tid",tid);
        b.putInt("noc",g);
        b.putString("tor",tor);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void goBack(View v){
        Intent intent=new Intent(this,calendar_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putInt("sid",sid);
        b.putStringArrayList("open",oh);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_time_page);
        slist=shop.getShops(customers_time_page.this);
        cl=customer.getCustomer(customers_time_page.this);
        Bundle bundle = getIntent().getExtras();
        oh= bundle.getStringArrayList("open");
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        dateb= bundle.getString("date");
        for(shop s: slist){
            if(s.getId()==sid){
                n=s.getName();
            }
        }
        for(customer c: cl) {
            if (c.getId() == id) {
                po = findViewById(R.id.textView25);
                po.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView26);
                b.setText(String.valueOf(user.getBalance(customers_time_page.this,id))+"\u20AC");
            }
        }
        text3 = findViewById(R.id.sname2);
        text3.setText(n);
        text4 = (TextView)findViewById(R.id.warning3);
        text5 = (TextView)findViewById(R.id.warning5);
    }
}