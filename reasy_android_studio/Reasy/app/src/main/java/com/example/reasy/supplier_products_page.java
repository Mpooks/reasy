package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class supplier_products_page extends AppCompatActivity {

    private main_lists ml;
    private ArrayList<supplier> supp_list = new ArrayList<>();
    private int id;
    private int supp_id;
    private String n;
    private ArrayList<product_supplier> arrayList;
    private LinearLayout linearLayout;
    private TextView text8;
    private String euro = Currency.getInstance(Locale.GERMANY).getCurrencyCode();

    public void chooseProductAndQuantity() {
    }

    public void createPrS() {
    }

    public void show() {
    }

    public void chooseSupplier() {
       // ml = main_lists.createLists();
        supp_list = (ArrayList<supplier>) ml.getSupplierList().clone();
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        supp_id = bundle.getInt("supp_id");

        for (supplier s : supp_list) {
            if (s.getId() == supp_id) {
                n = s.getName();
                arrayList = (ArrayList<product_supplier>) s.getSupplierProducts().clone();
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        text8 = findViewById(R.id.textView8);
        text8.setText(n + "'s Products:");

        for (int i = 0; i < arrayList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText((arrayList.get(i)).getName() + "      " + (arrayList.get(i)).getPrice()+" "+euro+"/piece");
            tv.setTextSize(26);
            tv.setHeight(192);
            tv.setWidth(1000);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_products_page);
        chooseSupplier();

    }

    public void onClick(View view) {
        Intent intent1 = new Intent(supplier_products_page.this, sample_request_page.class);
        //Create the bundle
        Bundle b1 = new Bundle();
        //Add your data to bundle
        b1.putInt("supp_id", supp_id);
        b1.putInt("id", id);
        intent1.putExtras(b1);
        startActivity(intent1);
    }

    public void goBack(View v) {
        Intent intent2 = new Intent(this, supplier_list_page.class);
        Bundle b2 = new Bundle();
        //Add your data to bundle
        b2.putInt("id", id);
        intent2.putExtras(b2);
        startActivity(intent2);
    }
}