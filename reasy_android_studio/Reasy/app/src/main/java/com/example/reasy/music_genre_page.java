package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class music_genre_page extends AppCompatActivity {

    private int id;

    public void chooseMusic(){}
    public void createReception(){}
    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_genre_page);
    }

    public void goBack(View v){
        Intent intent=new Intent(this, catering_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}