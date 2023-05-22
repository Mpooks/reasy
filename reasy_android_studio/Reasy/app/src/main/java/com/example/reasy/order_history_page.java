package com.example.reasy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class order_history_page extends AppCompatActivity {
    private int id,sid,oid;
    private main_lists ml;
    private menu smenu;
    private LinearLayout linearLayout;
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<Integer> orders,prods=new ArrayList<>();
    private ArrayList<product_menu> pms=new ArrayList<>();
    private ArrayList<order> oh,foundo=new ArrayList<>();
    public void newOrPrevOrder(View view){
        for(shop s:slist){
            if(s.getId()==sid){
                smenu=s.getShop_m();
                pms=smenu.getProducts();
                if(pms.isEmpty()) {
                prods.add(0);
                }else {
                    for (product_menu pm : pms) {
                        prods.add(pm.getId());
                    }
                }
            }
        }
        show();
    }
    public void show(){
        Intent intent=new Intent(this,shop_menu_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid",sid);
        b.putInt("id",id);
        b.putIntegerArrayList("orders",orders);
        b.putIntegerArrayList("prods",prods);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        orders= bundle.getIntegerArrayList("orders");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_page);
        linearLayout = findViewById(R.id.linear_layout);
        ml= main_lists.createLists();
        ul = (ArrayList<user>) ml.getUser_list().clone();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        cl = (ArrayList<customer>) ml.getCustomer_list().clone();
        if(orders.contains(0)){
            TextView tv = new TextView(this);
            tv.setText("You have no previous orders.");
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
        }
        else{
                for(customer c: cl){
                    if(c.getId()==id){
                        oh=c.getOrderHistory();
                    }
                }
            for(int i: orders){
                for(order o:oh){
                    if(o.getOrder_id()==i){
                        foundo.add(o);
                    }
                }
            }
            TextView b = new TextView(this);
            b.setText("You can select one of your previous orders: ");
            b.setTextSize(18);
            b.setHeight(192);
            b.setWidth(966);
            b.setPadding(30, 90, 30, 90);
            b.setGravity(Gravity.CENTER);
            b.setBackgroundResource(R.drawable.pt);
            b.setTextColor(Color.parseColor("#000000"));
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            b.setTypeface(typeface);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            b.setLayoutParams(lp);

            linearLayout.addView(b);
            for(order o: foundo){
                TextView tv = new TextView(this);
                tv.setText("");
                for(product_order po: o.getProducts())
                {
                    tv.setText(String.valueOf(tv.getText())+po.getName()+ "    "+ po.getPrice()+"\nQuantity: "+po.getQuantity()+"\n");
                }
                tv.setText(String.valueOf(tv.getText())+"Total cost: "+o.getCost()+"\n\n");
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(order_history_page.this,final_order_page.class);
                        oid=tv.getId();
                        //Create the bundle
                        Bundle b = new Bundle();
                        //Add your data to bundle
                        b.putInt("sid",sid);
                        b.putInt("id",id);
                        b.putIntegerArrayList("orders",orders);
                        b.putInt("oid",oid);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                tv.setTextSize(18);
                tv.setHeight(300);
                tv.setWidth(966);
                tv.setPadding(30, 20, 30, 20);
                tv.setId(o.getOrder_id());
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.menu_item);
                tv.setTypeface(typeface);
                tv.setLayoutParams(lp);

                linearLayout.addView(tv);
            }
        }
    }
    public void goBack(View v){
        Intent intent=new Intent(this,order_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}