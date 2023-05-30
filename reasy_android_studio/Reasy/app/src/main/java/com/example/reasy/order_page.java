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

import java.sql.SQLDataException;
import java.util.ArrayList;

public class order_page extends AppCompatActivity {
    private int id,sid,res_id,found=0,foundm=0,foundinlist=0;
    private String sname,om;
    private order fo;
    private LinearLayout linearLayout;
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<order> oh=new ArrayList<>(),nro=new ArrayList<>();
    private ArrayList<Integer> orders=new ArrayList<>();
    private ArrayList<reservation> resl=new ArrayList<reservation>(),rl=new ArrayList<reservation>();
    private CheckBox oc,ipc;
    private EditText rd;
    private  TextView text5,text4,po,b;
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
                oh=customer.getOrderHistory(order_page.this,id,sid);
                if(!(oh.isEmpty())) {
                    for (order p : oh) {
                        orders.add(p.getOrder_id());
                    }
                }
                else{
                    orders.add(0);
                }

                show();
            }
            else{
                DatabaseManager dbm = new DatabaseManager(order_page.this);
                try {
                    dbm.open();
                    dbm.insertOrder(sid,id,0,"In person","In person",res_id);
                    dbm.close();
                    popupMessage();
                } catch (SQLDataException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void show(){
        Intent intent=new Intent(this,order_history_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid",sid);
        b.putInt("id",id);
        b.putInt("res_id",res_id);
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
        ul=user.getUsers(order_page.this);
        slist=shop.getShops(order_page.this);
        cl=customer.getCustomer(order_page.this);
        for(customer c: cl) {
            if(c.getId()==id){
                resl=c.getReservations(order_page.this,id);
                po = findViewById(R.id.textView36);
                po.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView35);
                b.setText(String.valueOf(user.getBalance(order_page.this,id))+"\u20AC");
            }
        }
        nro=order.getOrderD(order_page.this,id);
        int foundr=0;
        for(reservation r: resl){
            for(order o: nro){
                if(o.getRes_id()==r.getReservation_id()){
                    foundr=1;
                }
            }
            if(foundr==0){
                rl.add(r);
            }
            foundr=0;
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
                Intent intent=new Intent(order_page.this,main_page.class);
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