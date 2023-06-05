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
    private int id;
    private ArrayList<Integer> rl=new ArrayList<>();
    LinearLayout linearLayout;
    private ArrayList<reservation> r = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejection_page);
        Bundle bundle = getIntent().getExtras();
        id=bundle.getInt("id");
        linearLayout = findViewById(R.id.linear_layout);
        ArrayList<supply> sl = new ArrayList<>();

        try {
            DatabaseManager dbm = new DatabaseManager(rejection_page.this);
            dbm.open();
            Cursor cursor=dbm.fetchSupH(id);

            if (cursor.moveToFirst()) {
                do {
                    sl.add(new supply(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5)));
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (supply o : sl) {
            TextView tv = new TextView(this);
            tv.setText("Supply Id: " + o.getSupplier_id());
            tv.setId(o.getSupplier_id());
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