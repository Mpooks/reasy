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
    private int id,sid,oid,res_id;
    private ArrayList<product_menu> arrayList=new ArrayList<>();
    private LinearLayout linearLayout;
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private product_menu pr_m;
    private ArrayList<Integer> orders,opr=new ArrayList<>(),oq=new ArrayList<>(),mpr=new ArrayList<>(),mq=new ArrayList<>();
    private ArrayList<Integer> prods=new ArrayList<>();
    private ArrayList<product_order> pro=new ArrayList<>();
    private ArrayList<order> oh,foundo=new ArrayList<>();
    private TextView po,b;
    public void newOrPrevOrder(View view){
        show();
    }
    public void show(){
        Intent intent=new Intent(this,shop_menu_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid",sid);
        b.putInt("id",id);
        b.putInt("res_id",res_id);
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
        res_id= bundle.getInt("res_id");
        orders= bundle.getIntegerArrayList("orders");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_page);
        linearLayout = findViewById(R.id.linear_layout);
        ul=user.getUsers(order_history_page.this);
        slist=shop.getShops(order_history_page.this);
        cl=customer.getCustomer(order_history_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                po = findViewById(R.id.textView30);
                po.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView29);
                b.setText(String.valueOf(user.getBalance(order_history_page.this,id))+"\u20AC");
            }
        }
        arrayList =menu.getMenu(order_history_page.this,sid);
        for (int i = 0; i < arrayList.size(); i++) {
            prods.add(arrayList.get(i).getId());
        }
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
            foundo=order.getOrder(order_history_page.this,id,sid);
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
                tv.setText("Order Id: "+o.getOrder_id()+ "    Cost: "+ o.getCost());
                tv.setId(o.getOrder_id());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        oid=o.getOrder_id();
                        pro=product_order.getOrderD(order_history_page.this,oid);
                        for(product_order p:pro){
                            opr.add(p.getId());
                            oq.add(p.getQuantity());
                            mpr.add(p.getId());
                            for (product_menu pm : arrayList) {
                                if (pm.getId() == p.getId()) {
                                    arrayList.set(arrayList.indexOf(pm), new product_menu(p.getId(), p.getName(), p.getPrice(), sid, pm.updateQuantity(p.getQuantity())));
                                }
                            }
                            pr_m = product_menu.getMPrD(order_history_page.this, p.getId(), sid);
                            mq.add(pr_m.updateQuantity(p.getQuantity()));
                        }
                        Intent intent=new Intent(order_history_page.this,final_order_page.class);

                        //Create the bundle
                        Bundle b = new Bundle();
                        //Add your data to bundle
                        b.putInt("sid",sid);
                        b.putInt("id",id);
                        b.putIntegerArrayList("orders",orders);
                        b.putIntegerArrayList("prods",prods);
                        b.putInt("oid",oid);
                        b.putIntegerArrayList("mpr",mpr);
                        b.putIntegerArrayList("mq",mq);
                        b.putIntegerArrayList("opr",opr);
                        b.putIntegerArrayList("oq",oq);
                        intent.putExtras(b);
                        startActivity(intent);
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
    public void goBack(View v){
        Intent intent=new Intent(this,order_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}