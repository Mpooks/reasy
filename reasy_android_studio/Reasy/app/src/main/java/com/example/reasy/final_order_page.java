package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class final_order_page extends AppCompatActivity {
    private int id,sid,foundp=0,foundq=0,f=0,oid,foundm=0,res_id;
    private ArrayList<Integer> orders,prods=new ArrayList<>(),opr=new ArrayList<>(),oq=new ArrayList<>(),mpr=new ArrayList<>(),mq=new ArrayList<>();
    private LinearLayout linearLayout;
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<order> oh=new ArrayList<>();
    private String pm;
    private double tc=0,bal;
    private order or;
    private ArrayList<product_menu> arrayList=new ArrayList<>();
    private CheckBox oc,ipc;
    private TextView text4,po,b;
    public void choosePaymentMethod(View v){
        oc=findViewById(R.id.online);
        ipc=findViewById(R.id.inperson);
        if(!(oc.isChecked())&&(!(ipc.isChecked()))){
            foundm=1;
            text4.setText("You must choose a payment method.");
        }
        else if((oc.isChecked())&&((ipc.isChecked()))){
            foundm=1;
            text4.setText("You must choose only one.");
        }
        else{
            foundm=0;
            text4.setText("");
            if(oc.isChecked()){
                pm="Online";
            }
            else{
                pm="In person";
            }
        }
        if(foundm==0){
            for(product_menu r: arrayList){
                tc=tc+r.getPrice()*oq.get(mpr.indexOf(r.getId()));
            }
            if(pm.compareTo("Online")==0){
                bal=user.getBalance(final_order_page.this,id);
                if(bal>=tc) {
                    user.setBalance(final_order_page.this,id,bal-tc);
                    order.createO(final_order_page.this,pm,"Online",tc,sid,id,res_id,arrayList,oq,mq,mpr);
                    popupMessage();
                }
               else{
                    show(v);
                }
            }
            else{
                order.createO(final_order_page.this,pm,"In person",tc,sid,id,res_id,arrayList,oq,mq,mpr);
                popupMessage();
            }

        }
    }
    public void show(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Your balance is insufficient!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(final_order_page.this,main_page.class);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order_page);
        Bundle bundle = getIntent().getExtras();
        text4=findViewById(R.id.name5);
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        oid=bundle.getInt("oid");
        res_id= bundle.getInt("res_id");
        orders=bundle.getIntegerArrayList("orders");
        prods=bundle.getIntegerArrayList("prods");
        opr=bundle.getIntegerArrayList("opr");
        mpr=bundle.getIntegerArrayList("mpr");
        oq=bundle.getIntegerArrayList("oq");
        mq=bundle.getIntegerArrayList("mq");
        linearLayout = findViewById(R.id.linear_layout);
        cl=customer.getCustomer(final_order_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                po = findViewById(R.id.textView63);
                po.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView64);
                b.setText(String.valueOf(user.getBalance(final_order_page.this,id))+"\u20AC");
            }
        }
        for(int pr: opr){
            arrayList.add(product_menu.getMPrD(final_order_page.this,pr,sid));
        }
        for(product_menu r: arrayList)
        {
            TextView tv = new TextView(this);
            tv.setText(r.getId()+"         "+r.getName()+"         "+r.getPrice()+"         "+oq.get(mpr.indexOf(r.getId())));
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(r.getId());
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.menu_item);
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            tv.setTypeface(typeface);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            linearLayout.addView(tv);
        }
    }
    public void goBack(View v){
        if(oid==0) {
            Intent intent = new Intent(this, shop_menu_page.class);
            //Create the bundle
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("sid", sid);
            b.putInt("id", id);
            b.putInt("res_id",res_id);
            b.putIntegerArrayList("orders", orders);
            b.putIntegerArrayList("prods", prods);
            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, order_history_page.class);
            //Create the bundle
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("sid", sid);
            b.putInt("id", id);
            b.putInt("res_id",res_id);
            b.putIntegerArrayList("orders", orders);
            b.putIntegerArrayList("prods", prods);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your order!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(final_order_page.this,main_page.class);
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