package com.example.reasy;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class rejection_page extends AppCompatActivity {
    private ArrayList<Integer> rl=new ArrayList<>();
    LinearLayout linearLayout;
    private ArrayList<reservation> r = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejection_page);
        Bundle bundle = getIntent().getExtras();
        rl= bundle.getIntegerArrayList("reservations");
        linearLayout = findViewById(R.id.linear_layout);
       /* try {
            dbm.open();
            Cursor cursor = dbm.fetchCustRes(id);


            if (cursor.moveToFirst()) {
                do {
                    r.add(new reservation(cursor.getInt(1), cursor.getInt(2), cursor.getInt(0), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }*/
        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int o : rl) {
            TextView tv = new TextView(this);
            //tv.setText("Resrvation Id: " + o.getReservation_id() + "    Number of guests: " + o.getNum_of_customers()+"    Time: "+o.getRTime()+ " "+o.getTID());
            tv.setId(o);
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