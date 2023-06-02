package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class shop_list_page extends AppCompatActivity {
    private int id,sid;
    private main_lists ml;
    private ArrayList<user> ul=new ArrayList<>();
    private ArrayList<reservation> arrayList;
    private LinearLayout linearLayout;
    ArrayList<rating> o = new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private String sname;

    public void showPrevReservations(){
        linearLayout = findViewById(R.id.linear_layout);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid=bundle.getInt("sid");
        cl=customer.getCustomer(shop_list_page.this);
        for(customer c:cl){
            try {
                DatabaseManager dbm = new DatabaseManager(shop_list_page.this);
                dbm.open();
                Cursor cursor=dbm.fetchRH(id,sid);

                if (cursor.moveToFirst()) {
                    do {
                        o.add(new rating(cursor.getInt(0),cursor.getInt(1),cursor.getDouble(2)));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                dbm.close();
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
        }
        for(rating c: o)
        {
            TextView b = new TextView(this);

            b.setText("Reservation id: "+c.getsid());
            b.setTextSize(18);
            b.setHeight(192);
            b.setWidth(966);
            b.setPadding(30, 90, 30, 90);
            b.setGravity(Gravity.CENTER);
            b.setBackgroundResource(R.drawable.menu_item);
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            b.setTypeface(typeface);
            b.setAllCaps(false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            b.setLayoutParams(lp);

            linearLayout.addView(b);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_page);
        showPrevReservations();
    }

    public void goBack(View v){
        Intent intent=new Intent(this,main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}

