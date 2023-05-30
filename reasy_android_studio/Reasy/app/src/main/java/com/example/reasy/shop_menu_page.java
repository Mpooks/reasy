package com.example.reasy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class shop_menu_page extends AppCompatActivity {
    private int id,sid,foundp=0,foundq=0,f=0,pid,qu,res_id;
    private ArrayList<Integer> orders,prods,opr=new ArrayList<>(),oq=new ArrayList<>(),mpr=new ArrayList<>(),mq=new ArrayList<>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private String name;
    private double cost;
    private product_menu pr_m;
    private LinearLayout linearLayout;
    private ArrayList<product_menu> arrayList=new ArrayList<>();
    private TextView po,b,text5;
    private EditText pr,q;
    public void chooseProductAndQuantity(View v){
        pr = findViewById(R.id.pid);
        q = findViewById(R.id.quan);
        if(pr.getText().toString().length()==0){
            foundp=1;
            text5.setText("Invalid choice.");
        }
        else {
            if (Integer.valueOf(pr.getText().toString()) <= 0) {
                foundp = 1;
                text5.setText("Invalid choice.");
            }
            for (int pm : prods) {
                if (Integer.valueOf(pr.getText().toString()) == pm) {
                    f++;
                    foundp = 0;
                    pid = Integer.valueOf(pr.getText().toString());
                    if (opr.contains(Integer.valueOf(pr.getText().toString()))) {
                        for (product_menu p : arrayList) {
                            if (p.getId() == Integer.valueOf(pr.getText().toString())) {
                                pr_m = p;
                            }
                        }
                    } else {
                        pr_m = product_menu.getMPrD(shop_menu_page.this, Integer.valueOf(pr.getText().toString()), sid);
                        name = pr_m.getName();
                        cost = pr_m.getPrice();
                    }
                }
            }
            if (f == 0) {
                foundp = 1;
                text5.setText("Invalid choice.");
            }
            if (foundp == 0) {
                if (q.getText().toString().length() == 0) {
                    foundq = 1;
                    text5.setText("Invalid choice.");
                } else if (Integer.valueOf(q.getText().toString()) <= 0) {
                    foundq = 1;
                    text5.setText("Invalid choice.");
                } else if (pr_m.getQuantity() >= Integer.valueOf(q.getText().toString())) {
                    foundq = 0;
                    text5.setText("");
                    qu = Integer.valueOf(q.getText().toString());
                } else {
                    foundq = 1;
                    text5.setText("Insufficient available quantity."+pr_m.getQuantity());
                }
            }
            if (foundq == 0 && foundp == 0) {
                createOrder();
                mpr.add(pid);
                for (product_menu p : arrayList) {
                    if (p.getId() == Integer.valueOf(pr.getText().toString())) {
                        arrayList.set(arrayList.indexOf(p), new product_menu(Integer.valueOf(pr.getText().toString()), p.getName(), p.getPrice(), sid, pr_m.updateQuantity(qu)));
                    }
                }
                mq.add(pr_m.updateQuantity(qu));
            }
        }
        foundp=0;
        foundq=0;
        f=0;
    }
    public void show(View v){
        if(opr.isEmpty()){
            text5.setText("You must select a product first.");
        }else {
            Intent intent = new Intent(this, final_order_page.class);
            //Create the bundle
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("sid", sid);
            b.putInt("id", id);
            b.putInt("res_id",res_id);
            b.putIntegerArrayList("orders", orders);
            b.putIntegerArrayList("prods", prods);
            b.putInt("oid", 0);
            b.putIntegerArrayList("mpr", mpr);
            b.putIntegerArrayList("mq", mq);
            b.putIntegerArrayList("opr", opr);
            b.putIntegerArrayList("oq", oq);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
    public void createOrder(){
        product_order po = new product_order(pid,pr_m.getName(),pr_m.getPrice(),0,qu);
        opr.add(pid);
        oq.add(qu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu_page);
        text5=findViewById(R.id.textView60);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        res_id= bundle.getInt("res_id");
        orders=bundle.getIntegerArrayList("orders");
        prods=bundle.getIntegerArrayList("prods");
        cl=customer.getCustomer(shop_menu_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                po = findViewById(R.id.textView38);
                po.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView37);
                b.setText(String.valueOf(user.getBalance(shop_menu_page.this,id))+"\u20AC");
            }
        }
        for(int pr: prods){
            arrayList.add(product_menu.getMPrD(shop_menu_page.this,pr,sid));
        }

        linearLayout = findViewById(R.id.linear_layout);
        for (int i = 0; i < arrayList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText((arrayList.get(i)).getId()+"         "+(arrayList.get(i)).getName()+"         "+(arrayList.get(i)).getPrice());
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(i);
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
        Intent intent=new Intent(this,order_history_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putInt("sid",sid);
        b.putInt("res_id",res_id);
        b.putIntegerArrayList("orders",orders);
        intent.putExtras(b);
        startActivity(intent);
    }

}