package com.example.reasy;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class organize_reception_page extends AppCompatActivity {

    private Button btn1,btn2;
     EditText t1,t2,t3,t4,t5;


    private int n1,n2,n3,n4, id;

    private String date;
    private ArrayList<reception_area> ral = new ArrayList<reception_area>();

    private ArrayList<Integer> ids = new ArrayList<Integer>();

    private ArrayList<reception_area> newDataList = new ArrayList<reception_area>();


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

        }

        else {
            try {
                 n1 = Integer.parseInt(t1.getText().toString());  //posa atoma thelw stin ekdilwsi
                 n2 = Integer.parseInt(t2.getText().toString());  //xronos pou thelw na ginei i ekdilwsi
                 n3 = Integer.parseInt(t3.getText().toString());  //mhnas pou thelw na ginei i ekdilwsi
                 n4 = Integer.parseInt(t4.getText().toString());  //mera pou thelw na ginei i ekdilwsi

                if (n2 < 2023 || n3 < 1 || n3 > 12 || n4 < 1 || n4 > 31) {
                    t5.setText("Please Try again");
                }
                    else if(((n3 == 4 || n3 == 6 || n3 == 9 || n3 == 11) && (n4 > 30 || n4 < 1)) || ((n2 % 4 == 0) && (n4 > 29) && (n3 == 2)) || ((n2 % 4 != 0) && (n4 > 28) && (n3 == 2)))
                        {
                            t5.setText("wrong day selection");

                        } else{
                            String year = Integer.toString(n2);
                            String month = Integer.toString(n3);
                            String day = Integer.toString(n4);


                            if (n3 >= 1 && n3 <= 9) {
                                month = "0" + month;
                            }
                            if (n4 >= 1 && n4 <= 9) {
                                day = "0" + day;
                            }

                            date = year.concat("-").concat(month).concat("-").concat(day);

                                t5.setText("NICE");

                                for (reception_area r : ral) {
                                    if(n1<=r.getNum_of_guests()){
                                        ids.add(r.getReception_area_id());
                                    }
                                }

                        }//end of small else
            }catch (NumberFormatException e) {}
        }//end of big else

    }//end of method
    public void show() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize_reception_page);



      // if ((String.valueOf(t5.getText()).compareTo("You are able to book a reception") == 0) || String.valueOf(t5.getText()).compareTo(null) == 0) {


            btn2 = (Button) findViewById(R.id.btn2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openReceptionAreaPage();
                }
            });
        }
    //}

        public void openReceptionAreaPage(){
            Intent intent2 = new Intent(this, reception_area_page.class);
            startActivity(intent2);
    }

    public ArrayList<Integer> getListIds() {
        return ids;
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