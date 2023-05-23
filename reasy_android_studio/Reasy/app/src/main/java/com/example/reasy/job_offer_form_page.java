package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class job_offer_form_page extends AppCompatActivity {

    private int id; //for back button


    public job_offer_form_page() {

    }
    public void fillForm(){

    }
    public void calcMedSalary(){

    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_form_page);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Waiter");
        arrayList.add("Bartender");
        arrayList.add("Barista");
        arrayList.add("Cashier");
        arrayList.add("Dishwasher");
        arrayList.add("Sous Chef");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedJO = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + selectedJO,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    public void goBack(View v){
        Intent intent=new Intent(this,shop_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}