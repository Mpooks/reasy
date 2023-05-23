package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class catering_page extends AppCompatActivity {

    private int id;

    public void chooseCatering(){}
    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_page);
    }

    public void goBack(View v){
        Intent intent=new Intent(this, cuisine_and_reception_type.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}