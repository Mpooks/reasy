package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cuisine_and_reception_type extends AppCompatActivity {

    private Button button_cart;
    private int id;

    public void chooseReceptionAndCuisineType(){}
    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine_and_reception_type);

        button_cart = (Button)findViewById(R.id.button_cart);
        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivy();

            }
        });
    }

    public void openNewActivy(){
        Intent intent = new Intent(this,catering.class);
        startActivity(intent);
    }

    public void goBack(View v){
        Intent intent=new Intent(this, reception_area_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}