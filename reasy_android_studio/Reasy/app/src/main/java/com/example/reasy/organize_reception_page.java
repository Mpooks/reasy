package com.example.reasy;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class organize_reception_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};

    private Button btn1;
    private EditText t1,t2,t3,t4,t5;

    public void chooseDateAndPeople(View v){
        t1 = (EditText)findViewById(R.id.t1);
        t2 = (EditText)findViewById(R.id.t2);
        t3 = (EditText)findViewById(R.id.t3);
        t4 = (EditText)findViewById(R.id.t4);

        t5 = (EditText)findViewById(R.id.t5);

        String input1 = t1.getText().toString();
        String input2 = t2.getText().toString();
        String input3 = t3.getText().toString();
        String input4 = t4.getText().toString();

        if(input1.isEmpty()|| input2.isEmpty() || input3.isEmpty() || input4.isEmpty()){
            t5.setText("Not all gaps are filled");

        }else {
            int n1,n2,n3,n4;
            try {

                 n1 = Integer.parseInt(t1.getText().toString());  //posa atoma thelw stin ekdilwsi
                 n2 = Integer.parseInt(t2.getText().toString());  //xronos pou thelw na ginei i ekdilwsi
                 n3 = Integer.parseInt(t3.getText().toString());  //mhnas pou thelw na ginei i ekdilwsi
                 n4 = Integer.parseInt(t4.getText().toString());  //mera pou thelw na ginei i ekdilwsi

                if (n2 < 2023 || n3 < 1 || n3 > 12 || n4 < 1 || n4 > 31) {
                    t5.setText("Please Try again");
                } else if (((n3 == 4 || n3 == 6 || n3 == 9 || n3 == 11) && (n4 > 30 || n4 < 1)) || ((n2 % 4 == 0) && (n4 > 29) && (n3 == 2)) || ((n2 % 4 != 0) && (n4 > 28) && (n3 == 2))) {
                    t5.setText("wrong day selection");

                } else {
                    String year = Integer.toString(n2);
                    String month = Integer.toString(n3);
                    String day = Integer.toString(n4);


                    if (n3 >= 1 && n3 <= 9) {
                        month = "0" + month;
                    }
                    if (n4 >= 1 && n4 <= 9) {
                        day = "0" + day;
                    }

                    String date;
                    date = year.concat("-").concat(month).concat("-").concat(day);
                    t5.setText(reception_area.getAvailability(n1, date));


                }//end of small else
            }catch (NumberFormatException e) {}
        }//end of big else

    }//end of method
    public void show() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize_reception_page);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(organize_reception_page.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}