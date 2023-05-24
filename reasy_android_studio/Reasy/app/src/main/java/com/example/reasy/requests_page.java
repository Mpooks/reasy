package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class requests_page extends AppCompatActivity {
    private int id,sid,cap,tid,nor,noc,max;
    private ArrayList<String> oh;
    private ArrayList<reservation> rl=new ArrayList<>();
    private ArrayList<table> tl=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<user> ul=new ArrayList<>();
    private ArrayList<shop> slist=new ArrayList<>();
    private main_lists ml;
    private TextView text3,text4;
    private EditText det;
    private String n,dateb,req,tor;
    public void fillRequests(View view){
        det = findViewById(R.id.details);
        req=String.valueOf(det.getText());
        for(customer c: cl) {
            if (c.getId() == id) {
                nor = c.getNumOfReservations();
            }
        }
        show();
    }
    public void show(){
        if(nor>=3){
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
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            create();
        }
    }
    public void create(){
        max = rl.get(0).getReservation_id();
        for (reservation r: rl) {
            if (r.getReservation_id() > max) {
                max = r.getReservation_id();
            }
        }
        max++;
        reservation r=new reservation(sid,id,max,noc,dateb,tor,tid,null,0,req);
        for(customer c: cl) {
            if (c.getId() == id) {
                c.updateNumOfReservations();
            }
        }
        for(user u: ul){

            /*if (u.getId() == id) {
                u.saveToUser(r);
            }
            if(u.getId() == sid){
                u.saveToUser(r);
            }*/
        }
        for (table t : tl) {
            if (t.getTable_id() == tid) {
                t.saveToTable(r);
            }
        }
        popupMessage();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_page);
        //ml= main_lists.createLists();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        cl = (ArrayList<customer>) ml.getCustomer_list().clone();
        rl= (ArrayList<reservation>) ml.getRes_list().clone();
        ul= (ArrayList<user>) ml.getUser_list().clone();
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
               // tl=s.getTables();
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
                finishAffinity();
                System.exit(0);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}