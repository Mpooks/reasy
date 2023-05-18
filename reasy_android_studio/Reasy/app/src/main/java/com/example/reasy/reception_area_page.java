package com.example.reasy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class reception_area_page extends AppCompatActivity {

    private Button btn1,bckbtn;
    private EditText t1,t2,t3,t4,t5;

     public void chooseReceptionArea(View v)
     {
         t1 = (EditText)findViewById(R.id.t1);
         t2 = (EditText)findViewById(R.id.t2);
         t3 = (EditText)findViewById(R.id.t3);
         t4 = (EditText)findViewById(R.id.t4);

         t5 = (EditText)findViewById(R.id.t5);

         int n1 = Integer.parseInt(t1.getText().toString());  //posa atoma thelw stin ekdilwsi

         int n2 = Integer.parseInt(t2.getText().toString());  //xronos pou thelw na ginei i ekdilwsi
         int n3 = Integer.parseInt(t3.getText().toString());  //mhnas pou thelw na ginei i ekdilwsi
         int n4 = Integer.parseInt(t4.getText().toString());  //mera pou thelw na ginei i ekdilwsi

         if(n2<2023|| n3<1 || n3>12 || n4<1 || n4>31){
             t5.setText("Please Try again");
         } else if (((n3==4 || n3 ==6 || n3==9 || n3==11)&&(n4>30 || n4<1)) || ((n2%4==0)&& (n4>29) && (n3==2))  || ((n2%4!=0)&& (n4>28) && (n3==2))) {
             t5.setText("wrong day selection");
         } else{
             String year = Integer.toString(n2);
             String month = Integer.toString(n3);
             String day = Integer.toString(n4);



             if(n3>=1 && n3<=9){
                 month = "0"+month;
             }
             if(n4>=1 && n4<=9){
                 day = "0"+day;
             }

             String date;
             date= year.concat("-").concat(month).concat("-").concat(day);
             t5.setText(reception_area.getAvailability(n1,date));

         }

     }


    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_area_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //back btn in ActionBar

    }
}