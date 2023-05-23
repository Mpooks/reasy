package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class reception_area_page extends AppCompatActivity {




    private ArrayList<String> area_name = new ArrayList<String>();

    private ArrayList<Integer> area_avaiblt = new ArrayList<Integer>();

    private String date;

    private EditText t6;
    private TextView t12;

    private int id; //for back button

    // Create an instance of organize_reception_page
    organize_reception_page receptionPage = new organize_reception_page();

    // Access the ids list
    ArrayList<Integer> ids = receptionPage.getListIds();

    public void chooseReceptionArea() {


    /*   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {           //start on item selected

                if (parent.getItemAtPosition(position).equals("None")) {
                    newItem = "None";
                } else {
                    //newItem = area_name.get(position);
                    newItem = parent.getItemAtPosition(position).toString();
                    for (reception_area r : idss) {

                    }

                    if (String.valueOf(t6.getText()).compareTo("You are able to book a reception") == 0) {
                        Toast.makeText(parent.getContext(), "Successfully Selected " + newItem, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(parent.getContext(), "Unsuccessfully Selected " + newItem, Toast.LENGTH_SHORT).show();

                    }

                }

            }//end on item selected

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });         */
    } // end of method


    public void show() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_area_page);


        // Retrieve the ids list from intent extras
        ArrayList<Integer> ids = getIntent().getIntegerArrayListExtra("ids");


        final Spinner spinner;
        //  ml = main_lists.createLists();
        //idss = (ArrayList<reception_area>) ml.getReceptionArea().clone();
        //int size = ids.size();
        String num_of_ra = Integer.toString(ids.size());
        area_name.add(num_of_ra);
        area_avaiblt.add(0);
     //   for (reception_area r : idss) {


        //    area_name.add("S = " + size);
        //    area_avaiblt.add((r.getNum_of_guests()));
      //  }


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(reception_area_page.this, android.R.layout.simple_spinner_dropdown_item,area_name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void goBack(View v){
        Intent intent=new Intent(this, organize_reception_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}