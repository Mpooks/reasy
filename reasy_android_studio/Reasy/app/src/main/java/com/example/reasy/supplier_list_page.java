package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class supplier_list_page extends AppCompatActivity {


    private LinearLayout linearLayout;
    private int id;
    private int supp_id;
    private main_lists ml;
    private ArrayList<supplier> supp_list = new ArrayList<>();

    public void chooseSupplier(){}
    public void show(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_list_page);
        linearLayout = findViewById(R.id.linear_layout);
        //ml= main_lists.createLists();
        supp_list = (ArrayList<supplier>) ml.getSupplierList().clone();
        for(supplier s: supp_list){
            Button tv = new Button(this);
            tv.setText(s.getName());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(supplier_list_page.this,supplier_products_page.class);
                    supp_id = tv.getId();
                    //Create the bundle
                    Bundle b = new Bundle();
                    //Add your data to bundle
                    b.putInt("supp_id",supp_id);
                    b.putInt("id",id);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(s.getId());
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