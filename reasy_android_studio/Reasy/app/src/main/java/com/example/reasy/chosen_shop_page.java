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
    private TextView text3,text4,text5;
    private LinearLayout linearLayout;
    private ArrayList<String> arrayList;
    user u=new user("mats@gmail.com","123","Matsuhisa Athens",2,1287,null);
    shop s=new shop("mats@gmail.com","123","Matsuhisa Athens",2,1287,null,"40, Apollonos street, Vouliagmeni 166 71","Athens",null,6,820,"Asian",7000,4500,2200,4.3,null,null,"2108960510",null);

    public void makeReservation(View v){
        Intent intent=new Intent(this,calendar_page.class);
        startActivity(intent);
    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String text= bundle.getString("stuff");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_shop_page);
        linearLayout = findViewById(R.id.linear_layout);
        if(text.compareTo("123")==0) {
            text3 = findViewById(R.id.textView3);
            String n=u.getName();
            String a=s.getAddress();
            String p=s.getPhone();
            text3.setText(n);
            text4 = findViewById(R.id.textView4);
            text4.setText("Address: "+a);
            text5 = findViewById(R.id.textView5);
            text5.setText("Telephone: "+p);
        }

        arrayList = new ArrayList<>();
        arrayList.add("1 Kg");
        arrayList.add("2 Kg");
        arrayList.add("3 Kg");
        arrayList.add("4 Kg");
        arrayList.add("5 Kg");
        arrayList.add("6 Kg");
        arrayList.add("7 Kg");
        arrayList.add("8 Kg");
        arrayList.add("9 Kg");
        arrayList.add("10 Kg");
        arrayList.add("11 Kg");
        arrayList.add("12 Kg");
        arrayList.add("13 Kg");
        arrayList.add("14 Kg");

        for (int i = 0; i < arrayList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(arrayList.get(i));
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
        Intent intent=new Intent(this,search_page.class);
        startActivity(intent);
    }
}