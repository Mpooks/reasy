package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class available_artists_page extends AppCompatActivity {

    private int id;

    public void chooseArtist(){}
    public void createReception(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_artists_page);
    }

    public void goBack(View v){
        Intent intent=new Intent(this, main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}