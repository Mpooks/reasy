package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class rating_form_page extends AppCompatActivity {

    public rating_form_page() {

    }
    public void chooseShopAndFillRating(){

    }
    public void show(){
        Intent intent=new Intent(this,rating_previous_orders_page.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form_page);
    }
}