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

public class requests_page extends AppCompatActivity {
    private int id,sid,cap,tid,nor,noc,ret;
    private ArrayList<String> oh;
    private ArrayList<table> tl=new ArrayList<>();
    private ArrayList<reservation> r=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<user> ul=new ArrayList<>();
    private ArrayList<shop> slist=new ArrayList<>();
    private TextView text3,text4,p,b;
    private EditText det;
    private String n,dateb,req,tor;
    public void fillRequests(View view){
        det = findViewById(R.id.details);
        req=String.valueOf(det.getText());
        nor = customer.getNumOfReservations(requests_page.this,id);
        show();
    }
    public void show(){
        if(nor%3==0 && nor!=0){
            Intent intent = new Intent(this, address_page.class);
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
            b.putString("req",req);
            b.putInt("nor",nor);
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            DatabaseManager dbm = new DatabaseManager(requests_page.this);
            try {
                dbm.open();
                dbm.insertRes(sid, id, noc, dateb, tor, tid, null, req);
                ret=customer.updateNumOfReservations(requests_page.this,id,nor);
                popupMessage();
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_page);
        cl=customer.getCustomer(requests_page.this);
        slist=shop.getShops(requests_page.this);
        ul= user.getUsers(requests_page.this);
        Bundle bundle = getIntent().getExtras();
        oh= bundle.getStringArrayList("open");
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        dateb= bundle.getString("date");
        cap= bundle.getInt("cap");
        tid= bundle.getInt("tid");
        noc=bundle.getInt("noc");
        tor=bundle.getString("tor");
        for(shop s: slist){
            if(s.getId()==sid){
                n=s.getName();
                tl=s.getTables(requests_page.this,sid);
            }
        }
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView31);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView32);
                b.setText(String.valueOf(c.getBalance())+"\u20AC");
            }
        }
        text3 = findViewById(R.id.sname3);
        text3.setText(n);
        text4 = findViewById(R.id.tv);
        text3.setText("Your assigned table is: "+tid);
    }
    public void goBack(View v){
        Intent intent=new Intent(this,customers_time_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putInt("sid", sid);
        b.putInt("cap", cap);
        b.putString("date", dateb);
        b.putStringArrayList("open", oh);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void popupMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your reservation!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(requests_page.this,main_page.class);
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