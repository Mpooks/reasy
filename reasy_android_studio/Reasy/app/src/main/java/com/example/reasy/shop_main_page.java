package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class shop_main_page extends AppCompatActivity {
    private int id;
    private ArrayList<supplier> sl=new ArrayList<>();
    private ArrayList<Integer> supl=new ArrayList<>();
    private ArrayList<String> sln=new ArrayList<>();

    public void createSupply(View v){
        sl=supplier.getSupplierList(shop_main_page.this);
        for(supplier s: sl){
            supl.add(s.getId());
            sln.add((s.getName()));
        }
        show();
    }
    public void show(){
        Intent intent=new Intent(this,supplier_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putIntegerArrayList("sl",supl);
        b.putStringArrayList("sln",sln);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void manageStaff(View v){
        Intent intent=new Intent(this,shop_calendar_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void addJobOffer(View v){
        Intent intent=new Intent(this,job_offer_form_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main_page);
    }
}