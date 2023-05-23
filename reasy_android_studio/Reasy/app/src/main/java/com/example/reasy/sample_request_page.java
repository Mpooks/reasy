package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sample_request_page extends AppCompatActivity {

    public void chooseSample(){}
    public void createSupply(){}
    public void show(){}
    private int id;
    private int supp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_request_page);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        supp_id = bundle.getInt("supp_id");
    }

    public void onClick(View view) {
        Intent intent1 = new Intent(sample_request_page.this, shipping_address_page.class);
        //Create the bundle
        Bundle b1 = new Bundle();
        //Add your data to bundle
        b1.putInt("supp_id", supp_id);
        b1.putInt("id", id);
        intent1.putExtras(b1);
        startActivity(intent1);
    }


    public void goBack(View v) {
        Intent intent = new Intent(this, supplier_products_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putInt("supp_id", supp_id);
        intent.putExtras(b);
        startActivity(intent);
    }
}