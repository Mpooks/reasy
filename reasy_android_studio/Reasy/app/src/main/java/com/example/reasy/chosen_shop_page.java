package com.example.reasy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class chosen_shop_page extends AppCompatActivity {
    private int id,sid;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<String> oh;
    private TextView text3,text4,text5;
    private LinearLayout linearLayout;
    private menu m;
    private ArrayList<product_menu> arrayList;
    private main_lists ml;
    private String n,a,p;

    public void makeReservation(){
        ml= main_lists.createLists();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        for(shop s: slist){
            if(s.getId()==sid){
                n=s.getName();
                a=s.getAddress();
                p=s.getPhone();
                m = s.getShop_m();
                arrayList=(ArrayList<product_menu>) m.getProducts().clone();
                oh=(ArrayList<String>) s.getOpeningHours();
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        text3 = findViewById(R.id.textView3);
        text3.setText(n);
        text4 = findViewById(R.id.textView4);
        text4.setText("Address: "+a);
        text5 = findViewById(R.id.textView5);
        text5.setText("Telephone: "+p);

        for (int i = 0; i < arrayList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText((arrayList.get(i)).getName()+"         "+(arrayList.get(i)).getPrice());
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
    public void show(View v){
        Intent intent=new Intent(this,calendar_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putInt("sid",sid);
        b.putStringArrayList("open",oh);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_shop_page);
        makeReservation();
    }
    public void goBack(View v){
        Intent intent=new Intent(this,search_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}