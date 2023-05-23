package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class shop_calendar_page extends AppCompatActivity {

    public void chooseDate(){}
    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_calendar_page);
    }

    public void onClick(View view) {
        Intent intent1 = new Intent(shop_calendar_page.this, table_list_page.class);
        startActivity(intent1);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, shop_main_page.class);
        startActivity(intent);
    }
}