package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class main_page extends AppCompatActivity {

    public void createInvitation(View v){
        Intent intent=new Intent(this,active_receptions_page.class);
        startActivity(intent);
    }
    public void show(){}
    public void rate(View v){
        Intent intent=new Intent(this,rating_form_page.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
    public void createRes(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    } //edw prepei na se paei se katasthmata

    public void createRec(View v){
        Intent intent=new Intent(this,organize_reception_page.class);
        startActivity(intent);
    }

    public void order(View v){
        Intent intent=new Intent(this,order_page.class);
        startActivity(intent);
    }
}