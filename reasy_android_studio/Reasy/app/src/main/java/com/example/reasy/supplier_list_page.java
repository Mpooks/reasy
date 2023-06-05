package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class supplier_list_page extends AppCompatActivity {


    private LinearLayout linearLayout;
    private int id,sid;
    private int supp_id;

    private TextView b;
    private ArrayList<supplier> supp_list = new ArrayList<>();
    private ArrayList<product_supplier> ps=new ArrayList<>();
    private ArrayList<Integer> sl=new ArrayList<>(),sp=new ArrayList<>();
    private ArrayList<shop> sli=new ArrayList<>();

    private ArrayList<String> sln=new ArrayList<>();

    public void chooseSupplier(int sid){
       ps=supplier.getSupplierProducts(supplier_list_page.this,sid);
       for(product_supplier p:ps){
           sp.add(p.getId());
       }
       show();
    }
    public void show(){
        Intent intent=new Intent(supplier_list_page.this,supplier_products_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid",sid);
        b.putInt("id", id);
        b.putIntegerArrayList("sl",sl);
        b.putStringArrayList("sln",sln);
        b.putIntegerArrayList("sp",sp);
        intent.putExtras(b);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_list_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        linearLayout = findViewById(R.id.linear_layout);
        sl=bundle.getIntegerArrayList("sl");
        sln=bundle.getStringArrayList("sln");
        sli=shop.getShops(supplier_list_page.this);
        for(shop c: sli) {
            if (c.getId() == id) {
                b = findViewById(R.id.but);
                b.setText(String.valueOf(user.getBalance(supplier_list_page.this,id))+"\u20AC");
            }
        }
        for(int s: sl){
            Button tv = new Button(this);
            tv.setText("Supplier id: "+s+" Name: "+sln.get(sl.indexOf(s)));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sid=s;
                    chooseSupplier(sid);
                }
            });
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setAllCaps(false);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(s);
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
        Intent intent = new Intent(this, shop_main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }
}