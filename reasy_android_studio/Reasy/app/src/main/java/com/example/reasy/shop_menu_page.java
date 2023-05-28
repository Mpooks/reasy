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
    private int id,sid;
    private ArrayList<Integer> orders,prods;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private LinearLayout linearLayout;
    private ArrayList<product_menu> arrayList=new ArrayList<>();
    private TextView po,b;
    private EditText pr,q;
    public void chooseProductAndQuantity(){

    }
    public void show(){

    }
    public void createOrder(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        orders=bundle.getIntegerArrayList("orders");
        prods=bundle.getIntegerArrayList("prods");
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
        b.putIntegerArrayList("orders",orders);
        intent.putExtras(b);
        startActivity(intent);
    }

}