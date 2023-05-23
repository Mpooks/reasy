package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class shipping_address_page extends AppCompatActivity {

    public void fill(){}
    public void createSupply(){}
    private int id;
    private int supp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_page);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        supp_id = bundle.getInt("supp_id");
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, sample_request_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putInt("supp_id", supp_id);
        intent.putExtras(b);
        startActivity(intent);
    }

}