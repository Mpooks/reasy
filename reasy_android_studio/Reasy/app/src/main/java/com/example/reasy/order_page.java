package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class order_page extends AppCompatActivity {
    private int id,sid,res_id,found=0,foundm=0,foundinlist=0,max,founds=0;
    private String sname,om;
    private main_lists ml;
    private order fo;
    private LinearLayout linearLayout;
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<order> oh=new ArrayList<>();
    private ArrayList<Integer> orders=new ArrayList<>();
    private ArrayList<reservation> rl=new ArrayList<reservation>();
    private CheckBox oc,ipc;
    private EditText rd;
    private  TextView text5,text4;
    public void createOrder(View view){
        foundinlist=0;
        rd=findViewById(R.id.rid);
        oc=findViewById(R.id.online);
        ipc=findViewById(R.id.inperson);

        if(rd.getText().toString().length()==0){
            found=1;
            text5.setText("You must pick a reservation first.");
        }
        else{
            res_id=Integer.valueOf(rd.getText().toString());
            for(reservation r: rl){
                if(r.getReservation_id()==res_id){
                    sid=r.getShop_id();
                    foundinlist=1;
                }
            }
            if(foundinlist==0){
                found=1;
                text5.setText("You must pick an existing reservation id.");
            }
            else{
                found=0;
                text5.setText("");
            }
        }
        if(!(oc.isChecked())&&(!(ipc.isChecked()))){
            foundm=1;
            text4.setText("You must choose an order method.");
        }
        else if((oc.isChecked())&&((ipc.isChecked()))){
            foundm=1;
            text4.setText("You must choose only one.");
        }
        else{
            foundm=0;
            text4.setText("");
            if(oc.isChecked()){
                om="Online";
            }
            else{
                om="In person";
            }
        }
        if(found==0 && foundm==0){
            if(om.compareTo("Online")==0){
                for(customer c: cl){
                    if(c.getId()==id){
                        oh=c.getOrderHistory();
                        if(!(c.getOrderHistory()==null)) {
                            for (order p : oh) {
                                if(p.getShop_id()==sid) {
                                    orders.add(p.getOrder_id());
                                    founds=1;
                                }
                            }
                            if(founds==0){
                                orders.add(0);
                            }
                        }
                        else{
                            orders.add(0);
                        }
                    }
                }
                show();
            }
            else{
                create();
            }
        }
    }
    public void create(){
        for(customer c: cl){
            if(!(c.getOrderHistory()==null)) {
                for (order o : c.getOrderHistory()) {
                    oh.add(o);
                }
            }
        }
        max = oh.get(0).getOrder_id();
        for (order o: oh) {
            if (o.getOrder_id() > max) {
                max = o.getOrder_id();
            }
        }
        max++;
        fo=new order(id,max,sid,0,"In person","In person",null);
        for(customer c: cl){
            if(c.getId()==id){
                c.addToOrderHistory(fo);
            }
        }
        popupMessage();
    }
    public void show(){
        Intent intent=new Intent(this,order_history_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid",sid);
        b.putInt("id",id);
        b.putIntegerArrayList("orders",orders);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        linearLayout = findViewById(R.id.linear_layout);
        ml= main_lists.createLists();
        ul = (ArrayList<user>) ml.getUser_list().clone();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        cl = (ArrayList<customer>) ml.getCustomer_list().clone();
        for(user u: ul) {
            if(u.getId()==id){
                rl=u.getReservations();
            }
        }
        for(reservation r: rl)
        {
            TextView tv = new TextView(this);
            sid=r.getShop_id();
            for(shop s: slist){
                if(s.getId()==sid){
                    sname=s.getName();
                }
            }
            tv.setText("Reservation id: "+r.getReservation_id()+"\nShop: "+sname);
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(r.getReservation_id());
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.menu_item);
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            tv.setTypeface(typeface);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            linearLayout.addView(tv);
        }
        text5 = (TextView)findViewById(R.id.name3);
        text4 = (TextView)findViewById(R.id.name4);
    }
    public void goBack(View v){
        Intent intent=new Intent(this,main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your order!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

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