package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class reception_area_page extends AppCompatActivity {
    private LinearLayout linearLayout;
    private int id,g;
    private double bal,cost;
    private String date;
    private TextView p,b;
    private ArrayList<customer> cl=new ArrayList<>();


    private ArrayList<Integer> ar_r=new ArrayList<>();
    private ArrayList<String> ar_n=new ArrayList<>();
    private ArrayList<reception_area> ra=new ArrayList<>();

    public void chooseReceptionArea() {
        linearLayout = findViewById(R.id.linear_layout);
        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (Integer o : ar_r) {
            TextView tv = new TextView(this);
            tv.setText("Area Id: " + o + "    Name: " + ar_n.get(ar_r.indexOf(o)));
            tv.setId(o);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bal = user.getBalance(reception_area_page.this, id);
                    cost = reception_area.getCost(reception_area_page.this,o);
                    if (bal >= cost) {
                        user.setBalance(reception_area_page.this, id, bal - cost);
                        show(o);
                    } else {
                        popupMessage();
                    }
                }
            });
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


    public void show(int o) {
        Intent intent = new Intent(reception_area_page.this, additional_reception_info_page.class);

        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putString("date", date);
        b.putIntegerArrayList("ar_r", ar_r);
        b.putInt("g", g);
        b.putStringArrayList("ar_n", ar_n);
        b.putInt("car",o);
        b.putDouble("cost",cost);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_area_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        g= bundle.getInt("g");
        date= bundle.getString("date");
        ar_r= bundle.getIntegerArrayList("ar_r");
        ar_n=bundle.getStringArrayList("ar_n");
        cl=customer.getCustomer(reception_area_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView58);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView59);
                b.setText(String.valueOf(user.getBalance(reception_area_page.this,id))+"\u20AC");
            }
        }
        chooseReceptionArea();
    }


    public void goBack(View v){
        Intent intent=new Intent(this, organize_reception_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Insufficient balance.");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(reception_area_page.this,main_page.class);
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