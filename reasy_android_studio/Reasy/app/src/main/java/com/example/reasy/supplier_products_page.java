package com.example.reasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class supplier_products_page extends AppCompatActivity {

    private LinearLayout linearLayout;
    private int id,sid,foundp=0,foundq=0,f=0,pid,qu,g;
    private int supp_id;
    private double cost;
    private String name;
    private ArrayList<supplier> supp_list = new ArrayList<>();
    private product_supplier cps;
    private ArrayList<product_supplier> ps=new ArrayList<>(),arrayList=new ArrayList<>();
    private ArrayList<Integer> sl=new ArrayList<>(),sp=new ArrayList<>();
    private ArrayList<Integer> spr=new ArrayList<>(),sq=new ArrayList<>(),supr=new ArrayList<>(),suq=new ArrayList<>();
    private ArrayList<shop> sli=new ArrayList<>();

    private ArrayList<String> sln=new ArrayList<>();
    private EditText pr,q;
    private double tc;
    private TextView text5,b;

    public void chooseProductAndQuantity(View view) {
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
            for (int pm : sp) {
                if (Integer.valueOf(pr.getText().toString()) == pm) {
                    f++;
                    foundp = 0;
                    pid = Integer.valueOf(pr.getText().toString());
                    for (product_supplier p : ps) {
                        if (p.getId() == Integer.valueOf(pr.getText().toString())) {
                            cps = p;
                        }
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
                } else if (cps.getQuantity() >= Integer.valueOf(q.getText().toString())) {
                    foundq = 0;
                    text5.setText("");
                    qu = Integer.valueOf(q.getText().toString());
                } else {
                    foundq = 1;
                    text5.setText("Insufficient available quantity.");
                }
            }
            if (foundq == 0 && foundp == 0) {
                product_supply.createPS(pid,cps.getName(),cps.getPrice(),qu,spr,sq);
                product_supplier.updateQuantity(qu,supr,pid,ps,sid,suq);
            }
        }
        foundp=0;
        foundq=0;
        f=0;
    }
    public void pay(View view) {
        if (spr.isEmpty()) {
            text5.setText("You must select a product first.");
        } else {
            g = shop.getSupplyHistory(supplier_products_page.this, id, sid);
            if (g == 1) {
                for(int pr: spr){
                    arrayList.add(product_supplier.getPrD(supplier_products_page.this,pr,sid));
                }
                for(product_supplier r: arrayList){
                    tc=tc+r.getPrice()*sq.get(spr.indexOf(r.getId()));
                }
                product_supplier.updatePSQ(supplier_products_page.this,arrayList,suq,supr);
                supply.createSupply(supplier_products_page.this,id,sid,"null","false",tc,arrayList,sq,spr);
                popupMessage();
            } else {
                show();
            }
        }
    }

    public void show() {
        Intent intent = new Intent(this, sample_request_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid", sid);
        b.putInt("id", id);
        b.putIntegerArrayList("sl", sl);
        b.putStringArrayList("sln", sln);
        b.putIntegerArrayList("sp", sp);
        b.putIntegerArrayList("spr", spr);
        b.putIntegerArrayList("sq", sq);
        b.putIntegerArrayList("supr", supr);
        b.putIntegerArrayList("suq", suq);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_products_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        linearLayout = findViewById(R.id.linear_layout);
        text5=findViewById(R.id.tv);
        sl=bundle.getIntegerArrayList("sl");
        sln=bundle.getStringArrayList("sln");
        sp=bundle.getIntegerArrayList("sp");
        sid=bundle.getInt("sid");
        sli=shop.getShops(supplier_products_page.this);
        for(shop c: sli) {
            if (c.getId() == id) {
                b = findViewById(R.id.textView47);
                b.setText(String.valueOf(user.getBalance(supplier_products_page.this,id))+"\u20AC");
            }
        }
        for(int pr: sp){
            ps.add(product_supplier.getPrD(supplier_products_page.this,pr,sid));
        }
        for (int i = 0; i < ps.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText((ps.get(i)).getId()+"         "+(ps.get(i)).getName()+"         "+(ps.get(i)).getPrice());
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


    public void goBack(View v) {
        Intent intent = new Intent(this, supplier_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putIntegerArrayList("sl",sl);
        b.putStringArrayList("sln",sln);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your order!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(supplier_products_page.this,shop_main_page.class);
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