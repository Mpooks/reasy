package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class table_list_page extends AppCompatActivity {

    public void chooseTable(){}
    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list_page);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, shop_calendar_page.class);
        startActivity(intent);
    }
}