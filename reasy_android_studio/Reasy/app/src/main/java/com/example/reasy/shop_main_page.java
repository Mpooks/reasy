package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class shop_main_page extends AppCompatActivity {

    public void createSupply(View v){
        Intent intent=new Intent(this,supplier_list_page.class);
        startActivity(intent);
    }

    public void manageStaff(View v){
        Intent intent=new Intent(this,shop_calendar_page.class);
        startActivity(intent);
    }

    public void addJobOffer(View v){
        Intent intent=new Intent(this,job_offer_form_page.class);
        startActivity(intent);
    }

    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main_page);
    }
}