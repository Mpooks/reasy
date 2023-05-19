package com.example.reasy;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class chosen_shop_page extends AppCompatActivity {
    private LinearLayout linearLayout;
    private ArrayList<String> arrayList;

    public void makeReservation(){

    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_shop_page);
        linearLayout = findViewById(R.id.linear_layout);

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
}