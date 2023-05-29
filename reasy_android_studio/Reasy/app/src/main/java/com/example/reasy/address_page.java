package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class address_page extends AppCompatActivity {
     private int id,sid,cap,tid,nor,noc,ret;
    private ArrayList<String> oh;
    private ArrayList<reservation> rl=new ArrayList<>();
    private ArrayList<table> tl=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<user> ul=new ArrayList<>();
    private ArrayList<shop> slist=new ArrayList<>();
    private TextView text3,text4,p,b;
    private EditText s,c;
    private String n,dateb,req,tor,str,ccity,scity;
    public void fillAddress(View view){
        s = findViewById(R.id.street);
        str=String.valueOf(s.getText());
        c = findViewById(R.id.city);
        ccity=String.valueOf(c.getText());
        if(str.length()==0 || ccity.length()==0){
            text4.setText("You have to fill the address.");
        }
        else if(scity.compareToIgnoreCase(ccity)==0) {
            DatabaseManager dbm = new DatabaseManager(address_page.this);
            try {
                dbm.open();
                dbm.insertRes(sid, id, noc, dateb, tor, tid, str, req);
                ret=customer.updateNumOfReservations(address_page.this,id,nor);
                popupMessage();
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            text4.setText("The address must be in the same city.");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_page);
        slist=shop.getShops(address_page.this);
        cl=customer.getCustomer(address_page.this);
        ul= user.getUsers(address_page.this);
        Bundle bundle = getIntent().getExtras();
        oh= bundle.getStringArrayList("open");
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        dateb= bundle.getString("date");
        cap= bundle.getInt("cap");
        tid= bundle.getInt("tid");
        noc=bundle.getInt("noc");
        tor=bundle.getString("tor");
        req=bundle.getString("req");
        nor=bundle.getInt("nor");
        for(shop s: slist){
            if(s.getId()==sid){
                n=s.getName();
            }
        }
        scity=shop.getCity(address_page.this,sid);
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView27);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView28);
                b.setText(String.valueOf(c.getBalance())+"\u20AC");
            }
        }

        text3 = findViewById(R.id.name);
        text3.setText(n);
        text4 = findViewById(R.id.name2);
    }
    public void goBack(View v){
        Intent intent=new Intent(this,requests_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putInt("sid", sid);
        b.putInt("cap", cap);
        b.putString("date", dateb);
        b.putStringArrayList("open", oh);
        b.putInt("tid",tid);
        b.putInt("noc",noc);
        b.putString("tor",tor);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void popupMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your reservation!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(address_page.this,main_page.class);
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
}