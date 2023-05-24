package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class main_page extends AppCompatActivity {
    private int id;

    public void createInvitation(View v){
        Intent intent=new Intent(this,active_receptions_page.class);
        startActivity(intent);
    }
    public void show(){}
    public void rate(View v){
        Intent intent=new Intent(this,shop_list_page.class);
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
        setContentView(R.layout.activity_main_page);
    }
    public void createRes(View v){
        Intent intent=new Intent(this,search_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void createRec(View v){
        Intent intent=new Intent(this,organize_reception_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void order(View v){
        Intent intent=new Intent(this,order_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }
}