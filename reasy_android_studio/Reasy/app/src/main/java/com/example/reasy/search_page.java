package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class search_page extends AppCompatActivity {
    public void chosenS(View v){
        Intent intent=new Intent(this,chosen_shop_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putString("stuff", "123");
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
    }
    public void goBack(View v){
        Intent intent=new Intent(this,main_page.class);
        startActivity(intent);
    }
}