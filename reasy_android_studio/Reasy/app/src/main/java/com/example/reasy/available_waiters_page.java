package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class available_waiters_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_waiters_page);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, table_reservations_page.class);
        startActivity(intent);
    }
}